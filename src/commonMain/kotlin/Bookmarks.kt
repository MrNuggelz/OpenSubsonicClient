package io.github.mrnuggelz.opensubsonic

import io.github.mrnuggelz.opensubsonic.responses.Song
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

public suspend fun OpenSubsonicClient.bookmarks(): Result<List<Bookmark>> =
    openSubsonicRequest<Bookmarks>("getBookmarks", "bookmarks").map { it.bookmarks }

public suspend fun OpenSubsonicClient.createBookmark(
    id: String,
    position: Long,
    comment: String? = null,
): Result<OpenSubsonicResponse> = openSubsonicRequest("createBookmark") {
    parameter("id", id)
    parameter("position", position)
    parameter("comment", comment)
}

public suspend fun OpenSubsonicClient.deleteBookmark(id: String): Result<OpenSubsonicResponse> =
    openSubsonicRequest("deleteBookmark") { parameter("id", id) }

public suspend fun OpenSubsonicClient.savePlayQueue(
    id: String,
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
