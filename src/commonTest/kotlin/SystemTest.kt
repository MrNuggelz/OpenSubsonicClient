import io.github.mrnuggelz.opensubsonic.License
import io.github.mrnuggelz.opensubsonic.OpenSubsonicClient
import io.github.mrnuggelz.opensubsonic.OpenSubsonicError
import io.github.mrnuggelz.opensubsonic.OpenSubsonicResponse
import io.github.mrnuggelz.opensubsonic.StatusResponse
import io.github.mrnuggelz.opensubsonic.license
import io.github.mrnuggelz.opensubsonic.ping
import io.kotest.core.spec.style.stringSpec
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.shouldBe
import kotlinx.datetime.Instant

val systemEndpointTestsFactory = stringSpec {
    "should handle wrong login credentials" {
        OpenSubsonicClient(
            OpenSubsonicMockEngine,
            "localhost",
            "wrong",
            "wrong",
            "testClient",
        ).ping() shouldBeFailure
            OpenSubsonicError.InvalidCredentials("Wrong username or password")
    }

    "should successfully execute ping command" {
        mockClient.ping() shouldBeSuccess {
            it shouldBe OpenSubsonicResponse(
                type = "AwesomeServerName",
                serverVersion = "0.1.3 (tag)",
                openSubsonic = true,
                status = StatusResponse.OK,
                version = "1.16.1"
            )
        }
    }

    "should successfully getLicense" {
        mockClient.license() shouldBeSuccess {
            it shouldBe License(
                valid = true,
                email = "demo@demo.org",
                licenseExpires = Instant.parse("2017-04-11T10:42:50.842Z"),
                trialExpires = Instant.parse("2017-04-11T10:42:50.842Z"),
            )
        }
    }
}
