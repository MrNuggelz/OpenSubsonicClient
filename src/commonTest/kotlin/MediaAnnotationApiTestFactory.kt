import io.github.mrnuggelz.opensubsonic.scrobble
import io.github.mrnuggelz.opensubsonic.setRating
import io.github.mrnuggelz.opensubsonic.star
import io.github.mrnuggelz.opensubsonic.unstar
import io.kotest.core.spec.style.stringSpec

val mediaAnnotationApiTestFactory = stringSpec {
    expectResponse("star", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        star(albumId = "1")
    }
    expectResponse("unstar", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        unstar(albumId = "1")
    }
    expectResponse("setRating", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        setRating("someId", 1)
    }
    expectResponse("scrobble", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        scrobble("someId")
    }
}
