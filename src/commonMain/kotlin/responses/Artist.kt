package io.github.mrnuggelz.opensubsonic.responses

import io.github.mrnuggelz.opensubsonic.CoverArtId
import io.github.mrnuggelz.opensubsonic.SongAlbumArtistId
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
public data class Artist(
    val id: ArtistId,
    val name: String,
    val artistImageUrl: String? = null,
    val starred: Instant? = null,
    val userRating: Int? = null,
    val averageRating: Double? = null,
)

@Serializable
public data class ArtistID3(
    val id: ArtistId,
    val name: String,
    val coverArt: CoverArtId? = null,
    val artistImageUrl: String? = null,
    val albumCount: Int? = null,
    val starred: String? = null,
    val musicBrainzId: String? = null,
    val sortName: String? = null,
    val roles: List<String>? = null,
)

@JvmInline
@Serializable
public value class ArtistId(public override val value: String) : SongAlbumArtistId
