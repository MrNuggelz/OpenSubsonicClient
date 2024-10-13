import io.github.mrnuggelz.opensubsonic.License
import io.github.mrnuggelz.opensubsonic.OpenSubsonicClient
import io.github.mrnuggelz.opensubsonic.OpenSubsonicError
import io.github.mrnuggelz.opensubsonic.license
import io.github.mrnuggelz.opensubsonic.ping
import io.kotest.core.spec.style.stringSpec
import kotlinx.datetime.Instant

val systemAPITestFactory = stringSpec {
    expectResponse(
        "ping with wrong credentials",
        "InvalidCredentials error",
        OpenSubsonicError.InvalidCredentials("Wrong username or password"),
        OpenSubsonicClient(
            OpenSubsonicMockEngine,
            "localhost",
            "wrong",
            "wrong",
            "testClient"
        ),
    ) { ping() }

    expectResponse("ping", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        ping()
    }

    expectResponse(
        "license",
        "subsonic license",
        License(
            valid = true,
            email = "demo@demo.org",
            licenseExpires = Instant.parse("2017-04-11T10:42:50.842Z"),
            trialExpires = Instant.parse("2017-04-11T10:42:50.842Z"),
        )
    ) { license() }
}
