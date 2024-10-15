package io.github.mrnuggelz.opensubsonic

import io.github.mrnuggelz.opensubsonic.responses.PlaylistWithSongs
import io.github.mrnuggelz.opensubsonic.responses.SongId
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

public suspend fun OpenSubsonicClient.playlists(username: String? = null): Result<List<Playlist>> =
    openSubsonicRequest<Playlists>("getPlaylists", "playlists") { parameter("username", username) }.map { it.playlists }

public suspend fun OpenSubsonicClient.playlist(id: PlaylistId): Result<PlaylistWithSongs> =
    openSubsonicRequest("getPlaylist", "playlist") { parameter("id", id) }

public suspend fun OpenSubsonicClient.createPlaylist(
    name: String,
    songIds: Iterable<SongId>
): Result<PlaylistWithSongs> =
    openSubsonicRequest("createPlaylist", "playlist") {
        append("name", name)
        appendAll("songId", songIds.map { it.value })
    }

public suspend fun OpenSubsonicClient.updatePlaylist(
    playlistId: PlaylistId,
    playlistName: String? = null,
    comment: String? = null,
    public: Boolean? = null,
    songIdToAdd: SongId? = null,
    songIndexToRemove: Int? = null,
): Result<OpenSubsonicResponse> =
    openSubsonicRequest(
        "updatePlaylist"
    ) {
        parameter("playlistId", playlistId)
        append("public", public.toString())
        append("songIndexToRemove", songIndexToRemove.toString())
        parameter("playlistName", playlistName)
        parameter("comment", comment)
        parameter("songIdToAdd", songIdToAdd)
    }

public suspend fun OpenSubsonicClient.deletePlaylist(id: PlaylistId): Result<OpenSubsonicResponse> =
    openSubsonicRequest("deletePlaylist") { parameter("id", id) }

@Serializable
private data class Playlists(
    @SerialName("playlist")
    val playlists: List<Playlist>,
)

@Serializable
public data class Playlist(
    val id: PlaylistId,
    val name: String,
    val comment: String? = null,
    val owner: String? = null,
    val public: Boolean? = null,
    val songCount: Int,
    val duration: Int,
    val created: Instant,
    val changed: Instant,
    val coverArt: CoverArtId? = null,
    val allowedUser: List<String> = emptyList(),
)

@JvmInline
@Serializable
public value class PlaylistId(public val value: String)
