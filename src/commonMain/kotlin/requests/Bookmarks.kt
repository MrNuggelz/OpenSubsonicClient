package io.github.mrnuggelz.opensubsonic.requests

import io.github.mrnuggelz.opensubsonic.OpenSubsonicClient
import io.github.mrnuggelz.opensubsonic.parameter
import io.github.mrnuggelz.opensubsonic.responses.OpenSubsonicResponse
import io.github.mrnuggelz.opensubsonic.responses.Song
import io.github.mrnuggelz.opensubsonic.responses.SongId
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Returns all bookmarks for this user. A bookmark is a position within a certain media file.
 */
public suspend fun OpenSubsonicClient.bookmarks(): Result<List<Bookmark>> =
    openSubsonicRequest<Bookmarks>("getBookmarks", "bookmarks").map { it.bookmarks }

/**
 * Creates or updates a bookmark (a position within a media file). Bookmarks are personal and not visible to other users.
 * @param position The position (in milliseconds) within the media file
 */
public suspend fun OpenSubsonicClient.createBookmark(
    id: SongId,
    position: Long,
    comment: String? = null,
): Result<OpenSubsonicResponse> = openSubsonicRequest("createBookmark") {
    parameter("id", id)
    parameter("position", position)
    parameter("comment", comment)
}

public suspend fun OpenSubsonicClient.deleteBookmark(id: SongId): Result<OpenSubsonicResponse> =
    openSubsonicRequest("deleteBookmark") { parameter("id", id) }

/**
 * Saves the state of the play queue for this user. This includes the tracks in the play queue, the currently playing track, and the position within this track. Typically used to allow a user to move between different clients/apps while retaining the same play queue (for instance when listening to an audio book).
 * @param id ID of a song in the play queue
 * @param current The ID of the current playing song.
 */
public suspend fun OpenSubsonicClient.savePlayQueue(
    id: SongId,
    current: String? = null,
    position: Long? = null,
): Result<OpenSubsonicResponse> = openSubsonicRequest("savePlayQueue") {
    parameter("id", id)
    parameter("position", position)
    parameter("current", current)
}

@Serializable
public data class Bookmark(
    val position: Long,
    val username: String,
    val comment: String? = null,
    val created: Instant,
    val changed: Instant,
    @SerialName("entry")
    val song: Song,
)

@Serializable
private data class Bookmarks(
    @SerialName("bookmark")
    val bookmarks: List<Bookmark>
)
