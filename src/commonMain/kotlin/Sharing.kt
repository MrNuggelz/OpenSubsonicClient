package io.github.mrnuggelz.opensubsonic

import io.github.mrnuggelz.opensubsonic.responses.Song
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

public suspend fun OpenSubsonicClient.shares(): Result<List<Share>> =
    openSubsonicRequest<Shares>("getShares", "shares").map { it.shares }

public suspend fun OpenSubsonicClient.createShare(
    id: String,
    description: String? = null,
    expires: Instant? = null,
): Result<List<Share>> = openSubsonicRequest<Shares>("createShare", "shares") {
    parameter("id", id)
    parameter("description", description)
    parameter("expires", expires)
}.map { it.shares }

public suspend fun OpenSubsonicClient.updateShare(
    id: String,
    description: String? = null,
    expires: Instant? = null
): Result<OpenSubsonicResponse> = openSubsonicRequest("updateShare") {
    parameter("id", id)
    parameter("description", description)
    parameter("expires", expires)
}

public suspend fun OpenSubsonicClient.deleteShare(id: String): Result<OpenSubsonicResponse> =
    openSubsonicRequest("deleteShare") { parameter("id", id) }

@Serializable
public data class Shares(
    @SerialName("share")
    val shares: List<Share>
)

@Serializable
public data class Share(
    val id: String,
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
