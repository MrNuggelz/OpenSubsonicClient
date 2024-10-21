package io.github.mrnuggelz.opensubsonic.requests

import io.kotest.core.spec.style.stringSpec

val internetRadioStationAPITestFactory = stringSpec {
    expectResponse(
        "internetRadioStations",
        "existing internet radio stations",
        listOf(
            InternetRadioStation(
                id = InternetRadioStationId("1"),
                name = "HBR1.com - Dream Factory",
                streamUrl = "http://ubuntu.hbr1.com:19800/ambient.aac",
                homePageUrl = "http://www.hbr1.com/"
            ),
            InternetRadioStation(
                id = InternetRadioStationId("2"),
                name = "HBR1.com - I.D.M. Tranceponder",
                streamUrl = "http://ubuntu.hbr1.com:19800/trance.ogg",
                homePageUrl = "http://www.hbr1.com/"
            ),
            InternetRadioStation(
                id = InternetRadioStationId("3"),
                name = "4ZZZ Community Radio",
                streamUrl = "https://stream.4zzz.org.au:9200/4zzz",
                homePageUrl = "https://4zzzfm.org.au"
            )
        )
    ) { internetRadioStations() }
    expectResponse("createInternetRadioStation", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        createInternetRadioStation(
            "https://stream.4zzz.org.au:9200/4zzz",
            "4ZZZ Community Radio",
            "https://4zzzfm.org.au"
        )
    }
    expectResponse("updateInternetRadioStation", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        updateInternetRadioStation(
            InternetRadioStationId("1"),
            "https://stream.4zzz.org.au:9200/4zzz",
            "4ZZZ Community Radio",
            "https://4zzzfm.org.au"
        )
    }
    expectResponse("deleteInternetRadioStation", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        deleteInternetRadioStation(InternetRadioStationId("1"))
    }
}
