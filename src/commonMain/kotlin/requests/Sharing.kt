package io.github.mrnuggelz.opensubsonic.requests

import io.github.mrnuggelz.opensubsonic.OpenSubsonicClient
import io.github.mrnuggelz.opensubsonic.parameter
import io.github.mrnuggelz.opensubsonic.responses.OpenSubsonicResponse
import io.github.mrnuggelz.opensubsonic.responses.Song
import io.github.mrnuggelz.opensubsonic.responses.SongAlbumId
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Returns information about shared media this user is allowed to manage.
 */
public suspend fun OpenSubsonicClient.shares(): Result<List<Share>> =
    openSubsonicRequest<Shares>("getShares", "shares").map { it.shares }

public suspend fun OpenSubsonicClient.createShare(
    id: SongAlbumId,
    description: String? = null,
    expires: Instant? = null,
): Result<List<Share>> = openSubsonicRequest<Shares>("createShare", "shares") {
    parameter("id", id)
    parameter("description", description)
    parameter("expires", expires)
}.map { it.shares }

public suspend fun OpenSubsonicClient.updateShare(
    id: ShareId,
    description: String? = null,
    expires: Instant? = null
): Result<OpenSubsonicResponse> = openSubsonicRequest("updateShare") {
    parameter("id", id)
    parameter("description", description)
    parameter("expires", expires)
}

public suspend fun OpenSubsonicClient.deleteShare(id: ShareId): Result<OpenSubsonicResponse> =
    openSubsonicRequest("deleteShare") { parameter("id", id) }

@Serializable
public data class Shares(
    @SerialName("share")
    val shares: List<Share>
)

@Serializable
public data class Share(
    val id: ShareId,
    val url: String,
    val description: String? = null,
    val username: String,
    val created: Instant,
    val expires: Instant? = null,
    val lastVisited: Instant? = null,
    val visitCount: Int,
    @SerialName("entry")
    val songs: List<Song>,
)

@JvmInline
@Serializable
public value class ShareId(public val value: String)
