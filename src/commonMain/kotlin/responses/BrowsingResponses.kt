package io.github.mrnuggelz.opensubsonic.responses

import kotlinx.serialization.Serializable

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
public data class ArtistIndexID3(
    val name: String,
    val artist: List<ArtistID3>,
)

@Serializable
public data class Artists(
    val ignoredArticles: String,
    val index: List<ArtistIndexID3> = emptyList(),
)
