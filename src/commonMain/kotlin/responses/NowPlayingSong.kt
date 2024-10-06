package io.github.mrnuggelz.opensubsonic.responses

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = NowPlayingSongSerializer::class)
public data class NowPlayingSong(
    val song: Song,
    val username: String,
    val minutesAgo: Int = 0,
    val playerId: Int = 0,
    val playerName: String? = null,
)

internal object NowPlayingSongSerializer : KSerializer<NowPlayingSong> {
    override val descriptor = buildClassSerialDescriptor(
        "io.github.mrnuggelz.opensubsonic.NowPlayingSong"
    ) {
        element("song", serialDescriptor<Song>())
        element("username", serialDescriptor<String>())
        element("minutesAgo", serialDescriptor<Int>())
        element("playerId", serialDescriptor<Int>())
        element("playerName", serialDescriptor<String?>())
    }

    @Serializable
    private data class NowPlayingSongData(
        val username: String,
        val minutesAgo: Int = 0,
        val playerId: Int = 0,
        val playerName: String? = null,
    )

    override fun deserialize(decoder: Decoder): NowPlayingSong {
        val song = Song.serializer().deserialize(decoder)
        return with(NowPlayingSongData.serializer().deserialize(decoder)) {
            NowPlayingSong(song, username, minutesAgo, playerId, playerName)
        }
    }

    override fun serialize(encoder: Encoder, value: NowPlayingSong) {
        error("should only be used to deserialize")
    }
}
