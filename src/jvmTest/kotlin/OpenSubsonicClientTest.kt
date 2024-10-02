import io.github.mrnuggelz.opensubsonic.OpenSubsonicClient
import io.github.mrnuggelz.opensubsonic.OpenSubsonicError
import io.github.mrnuggelz.opensubsonic.OpenSubsonicResponse
import io.github.mrnuggelz.opensubsonic.StatusResponse
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.shouldBe

class OpenSubsonicClientTest : StringSpec({
    "should handle wrong login credentials" {
        OpenSubsonicClient(
            OpenSubsonicMockEngine,
            "localhost",
            "wrong",
            "wrong",
            "testClient",
        ).ping() shouldBeFailure OpenSubsonicError.InvalidCredentials("Wrong username or password")
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
})
