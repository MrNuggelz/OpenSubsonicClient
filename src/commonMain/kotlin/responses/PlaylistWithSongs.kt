package io.github.mrnuggelz.opensubsonic.responses

import io.github.mrnuggelz.opensubsonic.requests.Playlist
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.serialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = PlaylistWithSongsSerializer::class)
public data class PlaylistWithSongs(
    val playlistData: Playlist,
    val songs: List<Song>,
)

internal object PlaylistWithSongsSerializer : KSerializer<PlaylistWithSongs> {
    override val descriptor = buildClassSerialDescriptor(
        "io.github.mrnuggelz.opensubsonic.responses.PlaylistWithSongs"
    ) {
        element("songs", serialDescriptor<List<Song>>())
        element("playlist", serialDescriptor<Playlist>())
    }

    @Serializable
    internal data class PlaylistSongs(
        val entry: List<Song> = emptyList(),
    )

    override fun deserialize(decoder: Decoder): PlaylistWithSongs {
        val songs = PlaylistSongs.serializer().deserialize(decoder)
        val playlist = Playlist.serializer().deserialize(decoder)
        return PlaylistWithSongs(playlist, songs.entry)
    }

    override fun serialize(encoder: Encoder, value: PlaylistWithSongs) {
        error("should only be used to deserialize")
    }
}
