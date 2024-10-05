package io.github.mrnuggelz.opensubsonic

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ParametersBuilder
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import org.kotlincrypto.hash.md.MD5

public open class OpenSubsonicClient(
    engine: HttpClientEngine,
    private val serverUrl: String,
    private val username: String,
    private val password: String,
    private val clientName: String,
    private val debugRequest: suspend (HttpResponse.() -> Unit) = {},
) {
    private val json = Json { ignoreUnknownKeys = true }
    private val client = HttpClient(engine) {
        defaultRequest {
            url("$serverUrl/rest/")
            url.parameters.run {
                append("u", username)
                append("c", clientName)
                append("f", "json")
                append("v", "1.16.1")
            }
        }
        install(ContentNegotiation) { json(json) }
    }

    internal suspend inline fun <reified T> openSubsonicRequest(
        path: String,
        fieldName: String? = null,
        crossinline additionalParameters: ParametersBuilder.() -> Unit = {},
    ): Result<T> = makeRequest(path, additionalParameters).mapCatching { it.parseResponse(fieldName) }

    private suspend inline fun makeRequest(
        path: String,
        crossinline additionalParameters: ParametersBuilder.() -> Unit = {},
    ): Result<HttpResponse> = runCatching {
        client.post(path) {
            generateSalt().let { salt ->
                parameter("t", password.plus(salt).md5Hash())
                parameter("s", salt)
            }
            url.parameters.additionalParameters()
        }.also { response ->
            debugRequest(response)
        }
    }

    private suspend inline fun <reified T> HttpResponse.parseResponse(
        fieldName: String? = null,
    ): T & Any {
        val resp = body<JsonObject>()["subsonic-response"] ?: error("field subsonic-response not found")

        resp.jsonObject["error"]?.let {
            throw json.decodeFromJsonElement(OpenSubsonicError.serializer(), it)
        }

        return if (fieldName != null) {
            resp.jsonObject[fieldName]
        } else {
            resp
        }?.let {
            json.decodeFromJsonElement<T>(it)
        } ?: error("no field error or $fieldName found")
    }
}

@OptIn(ExperimentalStdlibApi::class)
private fun String.md5Hash(): String =
    MD5().digest(encodeToByteArray()).toHexString()

private val chrs = ('A'..'Z') + ('a'..'z') + ('0'..'9')
private fun generateSalt() = generateSequence { chrs.random() }.take(9).joinToString("")

@Serializable
public data class OpenSubsonicResponse(
    val type: String,
    val serverVersion: String,
    val openSubsonic: Boolean,
    val status: StatusResponse,
    val version: String,
)

@Serializable
public enum class StatusResponse {
    @SerialName("ok")
    OK,

    @SerialName("failed")
    Failed,
}

@Serializable
public data class ErrorResponse(
    val error: OpenSubsonicError
)

@Serializable(with = OpenSubsonicError.OpenSubsonicErrorSerializer::class)
public abstract class OpenSubsonicError(override val message: String) : Throwable() {
    public data class GenericError(override val message: String) : OpenSubsonicError(message)
    public data class MissingParameter(override val message: String) : OpenSubsonicError(message)
    public data class OutdatedClient(override val message: String) : OpenSubsonicError(message)
    public data class OutdatedServer(override val message: String) : OpenSubsonicError(message)
    public data class InvalidCredentials(override val message: String) : OpenSubsonicError(message)
    public data class TokenUnsupported(override val message: String) : OpenSubsonicError(message)
    public data class NotAuthorized(override val message: String) : OpenSubsonicError(message)
    public data class TrialExpired(override val message: String) : OpenSubsonicError(message)
    public data class NotFound(override val message: String) : OpenSubsonicError(message)
    public data class UnknownError(override val message: String) : OpenSubsonicError(message)

    internal object OpenSubsonicErrorSerializer : KSerializer<OpenSubsonicError> {
        @Serializable
        private data class ErrorResponse(
            val code: Int,
            val message: String,
        )

        override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
            "io.github.mrnuggelz.opensubsonic.OpenSubsonicError"
        ) {
            element("code", serialDescriptor<Int>())
            element("message", serialDescriptor<String>())
        }

        override fun deserialize(decoder: Decoder): OpenSubsonicError {
            val error = ErrorResponse.serializer().deserialize(decoder)
            return when (error.code) {
                0 -> GenericError(error.message)
                10 -> MissingParameter(error.message)
                20 -> OutdatedClient(error.message)
                30 -> OutdatedServer(error.message)
                40 -> InvalidCredentials(error.message)
                41 -> TokenUnsupported(error.message)
                50 -> NotAuthorized(error.message)
                60 -> TrialExpired(error.message)
                70 -> NotFound(error.message)
                else -> UnknownError(error.message)
            }
        }

        override fun serialize(encoder: Encoder, value: OpenSubsonicError) {
            error("should not be used")
        }
    }
}
