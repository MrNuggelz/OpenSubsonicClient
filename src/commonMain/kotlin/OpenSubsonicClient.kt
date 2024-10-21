package io.github.mrnuggelz.opensubsonic

import io.github.mrnuggelz.opensubsonic.requests.CoverArtId
import io.github.mrnuggelz.opensubsonic.requests.InternetRadioStationId
import io.github.mrnuggelz.opensubsonic.requests.PlaylistId
import io.github.mrnuggelz.opensubsonic.requests.ShareId
import io.github.mrnuggelz.opensubsonic.responses.OpenSubsonicError
import io.github.mrnuggelz.opensubsonic.responses.SongAlbumArtistId
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsChannel
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.ParametersBuilder
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.datetime.Instant
import kotlinx.serialization.SerializationException
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

    internal suspend inline fun binaryOpenSubsonicRequest(
        path: String,
        crossinline additionalParameters: ParametersBuilder.() -> Unit = {},
    ): Result<ByteReadChannel> = makeRequest(path, additionalParameters).mapCatching { resp ->
        return when (resp.contentType()) {
            ContentType.Application.Json ->
                resp.body<JsonObject>()["subsonic-response"]?.jsonObject?.get("error")?.let {
                    Result.failure(json.decodeFromJsonElement(OpenSubsonicError.serializer(), it))
                } ?: error("field subsonic-response not found")

            else -> Result.success(resp.bodyAsChannel())
        }
    }

    internal suspend inline fun <reified T> openSubsonicRequest(
        path: String,
        fieldName: String? = null,
        crossinline additionalParameters: ParametersBuilder.() -> Unit = {},
    ): Result<T> = makeRequest(path, additionalParameters).mapCatching { resp ->
        runCatching {
            resp.parseResponse<T>(fieldName)
        }.onFailure {
            if (it is SerializationException) {
                println("error for response: ${resp.bodyAsText()}")
                it.printStackTrace()
            }
        }.getOrThrow()
    }

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

internal fun ParametersBuilder.parameter(key: String, value: String?) = value?.let { append(key, value) }
internal fun ParametersBuilder.parameter(key: String, value: SongAlbumArtistId?) = parameter(key, value?.value)
internal fun ParametersBuilder.parameter(key: String, value: InternetRadioStationId) = append(key, value.value)
internal fun ParametersBuilder.parameter(key: String, value: PlaylistId) = append(key, value.value)
internal fun ParametersBuilder.parameter(key: String, value: ShareId) = append(key, value.value)
internal fun ParametersBuilder.parameter(key: String, value: CoverArtId) = append(key, value.value)
internal fun ParametersBuilder.parameter(key: String, value: Boolean?) = parameter(key, value?.toString())
internal fun ParametersBuilder.parameter(key: String, value: Int?) = parameter(key, value?.toString())
internal fun ParametersBuilder.parameter(key: String, value: Long?) = parameter(key, value?.toString())
internal fun ParametersBuilder.parameter(key: String, value: Instant?) =
    parameter(key, value?.toEpochMilliseconds()?.toString())
