package io.github.mrnuggelz.opensubsonic.requests

import io.github.mrnuggelz.opensubsonic.mockClientID3
import io.github.mrnuggelz.opensubsonic.mockClientNonID3
import io.github.mrnuggelz.opensubsonic.responses.AlbumId
import io.github.mrnuggelz.opensubsonic.responses.SongId
import io.kotest.core.spec.style.stringSpec

val mediaAnnotationAPITestFactory = stringSpec {
    expectResponse("NonID3 - star", "OpensubsonicResponse", expectedOpenSubsonicResponse, mockClientNonID3) {
        star(id = AlbumId("1"))
    }
    expectResponse("ID3 - star", "OpensubsonicResponse", expectedOpenSubsonicResponse, mockClientID3) {
        star(id = SongId("1"))
    }
    expectResponse("NonID3 - unstar", "OpensubsonicResponse", expectedOpenSubsonicResponse, mockClientNonID3) {
        unstar(id = AlbumId("1"))
    }
    expectResponse("ID3 - unstar", "OpensubsonicResponse", expectedOpenSubsonicResponse, mockClientID3) {
        unstar(id = SongId("1"))
    }
    expectResponse("setRating", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        setRating(SongId("someId"), 1)
    }
    expectResponse("scrobble", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        scrobble(SongId("someId"))
    }
}
