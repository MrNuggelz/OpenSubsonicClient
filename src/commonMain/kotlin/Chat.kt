package io.github.mrnuggelz.opensubsonic

import io.github.mrnuggelz.opensubsonic.responses.InstantAsMillisecondsSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

public suspend fun OpenSubsonicClient.chatMessages(since: Instant? = null): Result<List<ChatMessage>> =
    openSubsonicRequest<ChatMessages>(
        "getChatMessages",
        "chatMessages"
    ) { parameter("since", since) }.map { it.messages }

public suspend fun OpenSubsonicClient.addChatMessage(message: String): Result<OpenSubsonicResponse> =
    openSubsonicRequest("addChatMessage") { append("message", message) }

@Serializable
public data class ChatMessage(
    val username: String,
    val message: String,
    @Serializable(with = InstantAsMillisecondsSerializer::class)
    val time: Instant,
)

@Serializable
private data class ChatMessages(
    @SerialName("chatMessage")
    val messages: List<ChatMessage>
)
