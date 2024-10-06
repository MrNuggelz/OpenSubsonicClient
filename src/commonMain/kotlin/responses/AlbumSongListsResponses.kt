package io.github.mrnuggelz.opensubsonic.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Starred(
    val artist: List<Artist> = emptyList(),
    val album: List<Song> = emptyList(),
    val song: List<Song> = emptyList()
)

@Serializable
public data class Starred2(
    val artist: List<ArtistID3> = emptyList(),
    val album: List<AlbumID3> = emptyList(),
    val song: List<Song> = emptyList()
)

@Serializable
public data class RandomSongs(@SerialName("song") val songs: List<Song> = emptyList())

@Serializable
internal data class NowPlaying(
    @SerialName("entry")
    val entries: List<NowPlayingSong> = emptyList(),
)

@Serializable
internal data class Songs(
    val song: List<Song> = emptyList(),
)
