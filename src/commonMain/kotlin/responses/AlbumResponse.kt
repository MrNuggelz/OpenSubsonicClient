package io.github.mrnuggelz.opensubsonic.responses

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
public data class AlbumList(
    val album: List<Song> = emptyList(),
)

@Serializable
public data class AlbumList2(
    val album: List<AlbumID3> = emptyList(),
)

@Serializable
public data class AlbumID3(
    val id: String = "",
    val name: String = "",
    val artist: String? = null,
    val artistId: String? = null,
    val coverArt: String? = null,
    val songCount: Int = 0,
    val duration: Int = 0,
    val playCount: Long? = 0L,
    val created: Instant? = null,
    val starred: Instant? = null,
    val year: Int? = 0,
    val genre: String? = null
)

@Serializable
public data class Album(
    val id: String,
    val name: String,
    val artist: String? = null,
    val artistId: String? = null,
    val coverArt: String? = null,
    val songCount: Int,
    val duration: Int,
    val playCount: Long? = null,
    val created: String,
    val starred: String? = null,
    val year: Int? = null,
    val genre: String? = null,
    val player: String? = null,
    val userRating: Int? = null,
    val recordLabels: List<RecordLabel> = emptyList(),
    val musicBrainzId: String? = null,
    val genres: List<ItemGenre>? = null,
    val artists: List<ArtistID3>? = null,
    val displayArtist: String? = null,
    val releaseType: List<String>? = null,
    val moods: List<String>? = null,
    val sortName: String? = null,
    val originalReleaseDate: ItemDate? = null,
    val releaseDate: ItemDate? = null,
    val isCompilation: Boolean? = null,
    val discTitles: List<DiscTitle> = emptyList(),
    val song: List<Song> = emptyList(),
)

@Serializable
public data class ItemDate(
    val year: Int? = null,
    val month: Int? = null,
    val day: Int? = null,
)

@Serializable
public data class DiscTitle(
    val disc: Int,
    val title: String,
)

@Serializable
public data class RecordLabel(val name: String)
