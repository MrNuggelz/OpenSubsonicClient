package io.github.mrnuggelz.opensubsonic.requests

import io.github.mrnuggelz.opensubsonic.OpenSubsonicClient
import io.github.mrnuggelz.opensubsonic.OpenSubsonicClientID3
import io.github.mrnuggelz.opensubsonic.OpenSubsonicClientNonID3
import io.github.mrnuggelz.opensubsonic.parameter
import io.github.mrnuggelz.opensubsonic.responses.AlbumId
import io.github.mrnuggelz.opensubsonic.responses.ArtistId
import io.github.mrnuggelz.opensubsonic.responses.OpenSubsonicResponse
import io.github.mrnuggelz.opensubsonic.responses.SongAlbumArtistId
import io.github.mrnuggelz.opensubsonic.responses.SongId
import kotlinx.datetime.Instant

/**
 * Attaches a star to a song, album or artist.
 * @param id The ID of the file (song) or folder (album/artist) to star.
 */
public suspend fun OpenSubsonicClientNonID3.star(
    id: SongAlbumArtistId? = null,
): Result<OpenSubsonicResponse> = openSubsonicRequest("star") {
    parameter("id", id)
}

/**
 * Attaches a star to a song, album or artist.
 * @param id The ID of the song to star.
 * @param albumId The ID of an album to star.
 * @param artistId The ID of an artist to star.
 */
public suspend fun OpenSubsonicClientID3.star(
    id: SongId? = null,
    albumId: AlbumId? = null,
    artistId: ArtistId? = null,
): Result<OpenSubsonicResponse> = openSubsonicRequest("star") {
    parameter("id", id)
    parameter("albumId", albumId)
    parameter("artistId", artistId)
}

/**
 * Removes a star to a song, album or artist.
 * @param id The ID of the file (song) or folder (album/artist) to star.
 */
public suspend fun OpenSubsonicClientNonID3.unstar(
    id: SongAlbumArtistId? = null,
): Result<OpenSubsonicResponse> = openSubsonicRequest("unstar") {
    parameter("id", id)
}

/**
 * Removes a star to a song, album or artist.
 * @param id The ID of the song to star.
 * @param albumId The ID of an album to star.
 * @param artistId The ID of an artist to star.
 */
public suspend fun OpenSubsonicClientID3.unstar(
    id: SongId? = null,
    albumId: AlbumId? = null,
    artistId: ArtistId? = null,
): Result<OpenSubsonicResponse> = openSubsonicRequest("unstar") {
    parameter("id", id)
    parameter("albumId", albumId)
    parameter("artistId", artistId)
}

/**
 * Sets the rating for a music file.
 * @param id The ID of the file (song) or folder (album/artist) to star.
 * @param rating The rating between 1 and 5 (inclusive), or 0 to remove the rating.
 */
public suspend fun OpenSubsonicClient.setRating(
    id: SongAlbumArtistId,
    rating: Int,
): Result<OpenSubsonicResponse> =
    openSubsonicRequest("setRating") {
        require(rating in (0..5)) { "rating must be between 0 and 5, but was $rating" }
        parameter("id", id)
        parameter("rating", rating)
    }

/**
 * Registers the local playback of one or more media files. Typically used when playing media that is cached on the client. This operation includes the following:
 *
 * - “Scrobbles” the media files on last.fm if the user has configured his/her last.fm credentials on the server.
 * - Updates the play count and last played timestamp for the media files. (Since 1.11.0)
 * - Makes the media files appear in the “Now playing” page in the web app, and appear in the list of songs returned by getNowPlaying (Since 1.11.0)
 *
 *  @param submission Whether this is a “submission” or a “now playing” notification.
 */
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
