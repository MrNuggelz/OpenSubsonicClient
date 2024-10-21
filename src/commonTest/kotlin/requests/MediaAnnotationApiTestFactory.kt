package io.github.mrnuggelz.opensubsonic.requests

import io.github.mrnuggelz.opensubsonic.responses.AlbumId
import io.github.mrnuggelz.opensubsonic.responses.SongId
import io.kotest.core.spec.style.stringSpec

val mediaAnnotationAPITestFactory = stringSpec {
    expectResponse("star", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        star(albumId = AlbumId("1"))
    }
    expectResponse("unstar", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        unstar(albumId = AlbumId("1"))
    }
    expectResponse("setRating", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        setRating(SongId("someId"), 1)
    }
    expectResponse("scrobble", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        scrobble(SongId("someId"))
    }
}
