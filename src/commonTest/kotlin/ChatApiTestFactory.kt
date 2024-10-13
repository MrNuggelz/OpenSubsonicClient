import io.github.mrnuggelz.opensubsonic.ChatMessage
import io.github.mrnuggelz.opensubsonic.addChatMessage
import io.github.mrnuggelz.opensubsonic.chatMessages
import io.kotest.core.spec.style.stringSpec
import kotlinx.datetime.Instant

val chatAPITestFactory = stringSpec {
    expectResponse(
        "chatMessages",
        "chat messages for user since the specified date",
        listOf(
            ChatMessage(
                username = "admin",
                time = Instant.fromEpochMilliseconds(1678935707000),
                message = "Api Script Testing"
            ),
            ChatMessage(
                username = "user",
                time = Instant.fromEpochMilliseconds(1678935699000),
                message = "Api Script Testing"
            )
        )
    ) {
        chatMessages(
            Instant.fromEpochMilliseconds(1678935707000)
        )
    }
    expectResponse(
        "addMChatMessage",
        "OpensubsonicResponse",
        expectedOpenSubsonicResponse
    ) { addChatMessage("hello world!") }
}
