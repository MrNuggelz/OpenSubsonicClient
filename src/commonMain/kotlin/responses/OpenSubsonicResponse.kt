package io.github.mrnuggelz.opensubsonic.responses

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

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

public interface SongAlbumId : SongAlbumArtistId
public interface SongAlbumArtistId {
    public val value: String
}
