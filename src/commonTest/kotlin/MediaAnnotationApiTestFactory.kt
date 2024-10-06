import io.github.mrnuggelz.opensubsonic.scrobble
import io.github.mrnuggelz.opensubsonic.setRating
import io.github.mrnuggelz.opensubsonic.star
import io.github.mrnuggelz.opensubsonic.unstar
import io.kotest.core.spec.style.stringSpec

val mediaAnnotationApiTestFactory = stringSpec {
    expectEndpointResponse("star", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        star(albumId = "1")
    }
    expectEndpointResponse("unstar", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        unstar(albumId = "1")
    }
    expectEndpointResponse("setRating", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        setRating("someId", 1)
    }
    expectEndpointResponse("scrobble", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        scrobble("someId")
    }
}
