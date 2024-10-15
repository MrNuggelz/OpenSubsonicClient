package io.github.mrnuggelz.opensubsonic

import io.github.mrnuggelz.opensubsonic.responses.AlbumId
import io.github.mrnuggelz.opensubsonic.responses.ArtistId
import io.github.mrnuggelz.opensubsonic.responses.SongId
import kotlinx.datetime.Instant

public suspend fun OpenSubsonicClient.star(
    id: SongAlbumArtistId? = null,
    albumId: AlbumId? = null,
    artistId: ArtistId? = null,
): Result<OpenSubsonicResponse> = openSubsonicRequest("star") {
    parameter("id", id)
    parameter("albumId", albumId)
    parameter("artistId", artistId)
}

public suspend fun OpenSubsonicClient.unstar(
    id: SongAlbumArtistId? = null,
    albumId: AlbumId? = null,
    artistId: ArtistId? = null,
): Result<OpenSubsonicResponse> = openSubsonicRequest("unstar") {
    parameter("id", id)
    parameter("albumId", albumId)
    parameter("artistId", artistId)
}

public suspend fun OpenSubsonicClient.setRating(
    id: SongAlbumArtistId,
    rating: Int,
): Result<OpenSubsonicResponse> =
    openSubsonicRequest("setRating") {
        require(rating in (1..5)) { "rating must be between 1 and 5, but was $rating" }
        parameter("id", id)
        parameter("rating", rating)
    }

public suspend fun OpenSubsonicClient.scrobble(
    id: SongId,
    time: Instant? = null,
    submission: Boolean = true,
): Result<OpenSubsonicResponse> =
    openSubsonicRequest("scrobble") {
        parameter("id", id)
        parameter("submission", submission)
        parameter("time", time)
    }
