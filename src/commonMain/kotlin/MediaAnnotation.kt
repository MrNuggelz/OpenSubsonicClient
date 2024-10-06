package io.github.mrnuggelz.opensubsonic

import kotlinx.datetime.Instant

public suspend fun OpenSubsonicClient.star(
    id: String? = null,
    albumId: String? = null,
    artistId: String? = null,
): Result<OpenSubsonicResponse> = openSubsonicRequest("star") {
    parameter("id", id)
    parameter("albumId", albumId)
    parameter("artistId", artistId)
}

public suspend fun OpenSubsonicClient.unstar(
    id: String? = null,
    albumId: String? = null,
    artistId: String? = null,
): Result<OpenSubsonicResponse> = openSubsonicRequest("unstar") {
    parameter("id", id)
    parameter("albumId", albumId)
    parameter("artistId", artistId)
}

public suspend fun OpenSubsonicClient.setRating(
    id: String,
    rating: Int,
): Result<OpenSubsonicResponse> =
    openSubsonicRequest("setRating") {
        require(rating in (1..5)) { "rating must be between 1 and 5, but was $rating" }
        append("id", id)
        parameter("rating", rating)
    }

public suspend fun OpenSubsonicClient.scrobble(
    id: String,
    time: Instant? = null,
    submission: Boolean = true,
): Result<OpenSubsonicResponse> =
    openSubsonicRequest("scrobble") {
        append("id", id)
        parameter("submission", submission)
        parameter("time", time)
    }
