package io.github.mrnuggelz.opensubsonic.responses

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Directory(
    val id: String,
    val parent: String? = null,
    val name: String,
    val starred: Instant? = null,
    val userRating: Int? = 0,
    val averageRating: Double? = 0.0,
    val playCount: Long? = 0L,
    @SerialName("child")
    val songs: List<Song>? = emptyList()
)

@Serializable
public data class ArtistInfo(
    val biography: String? = null,
    val musicBrainzId: String? = null,
    val lastFmUrl: String? = null,
    val smallImageUrl: String? = null,
    val mediumImageUrl: String? = null,
    val largeImageUrl: String? = null,
    val similarArtist: List<Artist> = emptyList(),
)

@Serializable
public data class ArtistInfo2(
    val biography: String? = null,
    val musicBrainzId: String? = null,
    val lastFmUrl: String? = null,
    val smallImageUrl: String? = null,
    val mediumImageUrl: String? = null,
    val largeImageUrl: String? = null,
    val similarArtist: List<ArtistID3> = emptyList(),
)

@Serializable
public data class AlbumInfo(
    val notes: String? = null,
    val musicBrainzId: String? = null,
    val lastFmUrl: String? = null,
    val smallImageUrl: String? = null,
    val mediumImageUrl: String? = null,
    val largeImageUrl: String? = null,
)

@Serializable
public data class Indexes(
    val lastModified: Long = 0L,
    val ignoredArticles: String = "",
    val shortcuts: List<Artist> = emptyList(),
    @SerialName("index")
    val indexes: List<ArtistIndexID3> = emptyList(),
    @SerialName("childs")
    val songs: List<Song> = emptyList()
)

@Serializable
public data class ArtistIndexID3(
    val name: String,
    val artist: List<ArtistID3>,
)

@Serializable
public data class Artists(
    val ignoredArticles: String,
    val index: List<ArtistIndexID3> = emptyList(),
)

@Serializable
public data class MusicFolder(
    val id: String,
    val name: String? = null,
)
