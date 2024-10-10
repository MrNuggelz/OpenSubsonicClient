package io.github.mrnuggelz.opensubsonic

import io.github.mrnuggelz.opensubsonic.responses.PlaylistWithSongs
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

public suspend fun OpenSubsonicClient.playlists(username: String? = null): Result<List<Playlist>> =
    openSubsonicRequest<Playlists>("getPlaylists", "playlists") { parameter("username", username) }.map { it.playlists }

public suspend fun OpenSubsonicClient.playlist(id: String): Result<PlaylistWithSongs> =
    openSubsonicRequest("getPlaylist", "playlist") { append("id", id) }

public suspend fun OpenSubsonicClient.createPlaylist(
    name: String,
    songIds: Iterable<String>
): Result<PlaylistWithSongs> =
    openSubsonicRequest("createPlaylist", "playlist") {
        append("name", name)
        appendAll("songId", songIds)
    }

public suspend fun OpenSubsonicClient.updatePlaylist(
    playlistId: String,
    playlistName: String? = null,
    comment: String? = null,
    public: Boolean? = null,
    songIdToAdd: String? = null,
    songIndexToRemove: Int? = null,
): Result<OpenSubsonicResponse> =
    openSubsonicRequest(
        "updatePlaylist"
    ) {
        append("playlistId", playlistId)
        append("public", public.toString())
        append("songIndexToRemove", songIndexToRemove.toString())
        parameter("playlistName", playlistName)
        parameter("comment", comment)
        parameter("songIdToAdd", songIdToAdd)
    }

public suspend fun OpenSubsonicClient.deletePlaylist(id: String): Result<OpenSubsonicResponse> =
    openSubsonicRequest("deletePlaylist") { append("id", id) }

@Serializable
private data class Playlists(
    @SerialName("playlist")
    val playlists: List<Playlist>,
)

@Serializable
public data class Playlist(
    val id: String,
    val name: String,
    val comment: String? = null,
    val owner: String? = null,
    val public: Boolean? = null,
    val songCount: Int,
    val duration: Int,
    val created: Instant,
    val changed: Instant,
    val coverArt: String? = null,
    val allowedUser: List<String> = emptyList(),
)
