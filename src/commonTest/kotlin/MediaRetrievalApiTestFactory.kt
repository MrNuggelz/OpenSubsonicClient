import io.github.mrnuggelz.opensubsonic.Lyrics
import io.github.mrnuggelz.opensubsonic.OpenSubsonicError
import io.github.mrnuggelz.opensubsonic.avatar
import io.github.mrnuggelz.opensubsonic.coverArt
import io.github.mrnuggelz.opensubsonic.download
import io.github.mrnuggelz.opensubsonic.lyrics
import io.github.mrnuggelz.opensubsonic.stream
import io.kotest.core.spec.style.stringSpec
import io.ktor.util.toByteArray
import kotlin.time.Duration.Companion.seconds

val mediaRetrievalAPITestFactory = stringSpec {
    expectResponse(
        "stream",
        "error if song is not found",
        OpenSubsonicError.NotFound("The request data was not found")
    ) { stream("notExisting") }
    expectResponse("stream", "existing shares", "someData".encodeToByteArray()) {
        stream(
            "songId",
            maxBitRate = 0,
            format = "mp3",
            timeOffset = 2.seconds,
        ).map { it.toByteArray() }
    }
    expectResponse(
        "download",
        "existing shares",
        "someData".encodeToByteArray()
    ) { download("songId").map { it.toByteArray() } }
    expectResponse(
        "getCoverArt",
        "existing shares",
        "someData".encodeToByteArray()
    ) { coverArt("coverArtId").map { it.toByteArray() } }
    expectResponse(
        "getLyrics",
        "existing shares",
        @Suppress("MaximumLineLength")
        Lyrics(
            artist = "Metallica",
            title = "Blitzkrieg",
            value = "Let us have peace, let us have life\n\nLet us escape the cruel night\n\nLet us have time, let the sun shine\n\nLet us beware the deadly sign\n\n\n\nThe day is coming\n\nArmageddon's near\n\nInferno's coming\n\nCan we survive the blitzkrieg?\n\nThe blitzkrieg\n\nThe blitzkrieg\n\n\n\nSave us from fate, save us from hate\n\nSave ourselves before it's too late\n\nCome to our need, hear our plea\n\nSave ourselves before the earth bleeds\n\n\n\nThe day is dawning\n\nThe time is near\n\nAliens calling\n\nCan we survive the blitzkrieg?"
        )
    ) { lyrics("Metallica", "Blitzkrieg") }
    expectResponse(
        "getAvatar",
        "existing shares",
        "someData".encodeToByteArray()
    ) { avatar("guest").map { it.toByteArray() } }
}
