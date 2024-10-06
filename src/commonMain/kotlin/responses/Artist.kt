package io.github.mrnuggelz.opensubsonic.responses

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
public data class Artist(
    val id: String,
    val name: String,
    val artistImageUrl: String? = null,
    val starred: Instant? = null,
    val userRating: Int? = null,
    val averageRating: Double? = null,
)

@Serializable
public data class ArtistID3(
    val id: String,
    val name: String,
    val coverArt: String? = null,
    val artistImageUrl: String? = null,
    val albumCount: Int? = null,
    val starred: String? = null,
    val musicBrainzId: String? = null,
    val sortName: String? = null,
    val roles: List<String>? = null,
)
