package io.github.mrnuggelz.opensubsonic.requests

import io.github.mrnuggelz.opensubsonic.responses.SongId
import io.kotest.core.spec.style.stringSpec
import responseexpectations.expectedShares

val shareAPITestFactory = stringSpec {
    expectResponse("shares", "existing shares", expectedShares) { shares() }
    expectResponse("createShare", "existing shares", expectedShares) { createShare(SongId("songId")) }

    expectResponse("updateShare", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        updateShare(ShareId("shareId"))
    }

    expectResponse("deleteShare", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        deleteShare(ShareId("shareId"))
    }
}
