import io.github.mrnuggelz.opensubsonic.Bookmark
import io.github.mrnuggelz.opensubsonic.bookmarks
import io.github.mrnuggelz.opensubsonic.createBookmark
import io.github.mrnuggelz.opensubsonic.deleteBookmark
import io.github.mrnuggelz.opensubsonic.savePlayQueue
import io.kotest.core.spec.style.stringSpec
import kotlinx.datetime.Instant
import responseexpectations.songIllwiththeSkills
import responseexpectations.songStayOutHere

val bookmarkAPITestFactory = stringSpec {
    expectResponse(
        "bookmarks",
        "existing bookmarks",
        listOf(
            Bookmark(
                position = 129000,
                username = "demo",
                comment = "",
                created = Instant.parse("2023-03-13T16:30:35Z"),
                changed = Instant.parse("2023-03-13T16:30:35Z"),
                song = songStayOutHere
            ),
            Bookmark(
                position = 7000,
                username = "demo",
                comment = "playSub bookmark",
                created = Instant.parse("2023-03-25T11:44:51Z"),
                changed = Instant.parse("2023-03-25T11:44:51Z"),
                song = songIllwiththeSkills
            )
        )
    ) { bookmarks() }
    expectResponse("createBookmark", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        createBookmark("123", 7000, "playSub bookmark")
    }
    expectResponse("deleteBookmark", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        deleteBookmark("123")
    }
    expectResponse("savePlayQueue", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        savePlayQueue("123", "123", 7000)
    }
}
