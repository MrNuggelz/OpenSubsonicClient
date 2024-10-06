import io.github.mrnuggelz.opensubsonic.OpenSubsonicClient
import io.github.mrnuggelz.opensubsonic.OpenSubsonicResponse
import io.github.mrnuggelz.opensubsonic.StatusResponse
import io.kotest.core.spec.style.scopes.StringSpecRootScope
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldStartWith
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.http.Url
import io.ktor.http.headersOf
import io.ktor.http.parameters
import io.ktor.util.filter
import org.kotlincrypto.hash.md.MD5

val OpenSubsonicMockEngine = MockEngine { request ->
    val body: String = if (request.url.parameters.validateDefaultParameters()) {
        request.url.toString().shouldStartWith("http://localhost/rest/")
        request.url.handlePath()
    } else {
        wrongCredentialResponse
    }
    respond(
        content = body,
        status = HttpStatusCode.OK,
        headers = headersOf(HttpHeaders.ContentType, "application/json")
    )
}

val mockClient = OpenSubsonicClient(
    OpenSubsonicMockEngine,
    "http://localhost",
    "test",
    "sesame",
    "testClient",
)

fun Url.handleParameters(expectedParameters: Parameters, response: String): String =
    if (parameters.filter { name, _ -> name !in listOf("u", "c", "f", "v", "s", "t") } == expectedParameters) {
        response
    } else {
        notFoundErrorResponse
    }

fun Parameters.validateDefaultParameters() = filter { name, _ -> name in listOf("u", "c", "f", "v") } == parameters {
    append("u", "test")
    append("c", "testClient")
    append("f", "json")
    append("v", "1.16.1")
} && validatePassword()

@OptIn(ExperimentalStdlibApi::class)
fun Parameters.validatePassword() =
    MD5().digest("sesame".plus(get("s")).encodeToByteArray()).toHexString() == get("t")

private fun errorResponse(code: Int, message: String) = """
{
  "subsonic-response": {
    "status": "ok",
    "version": "1.16.1",
    "type": "AwesomeServerName",
    "serverVersion": "0.1.3 (tag)",
    "openSubsonic": true,
    "error": {
      "code": $code,
      "message": "$message"
    }
  }
}
"""

val genericError = errorResponse(0, "A generic error")
val wrongCredentialResponse = errorResponse(40, "Wrong username or password")
val notFoundErrorResponse = errorResponse(70, "The request data was not found")

const val SubsonicResponse = """
{
  "subsonic-response": {
    "status": "ok",
    "version": "1.16.1",
    "type": "AwesomeServerName",
    "serverVersion": "0.1.3 (tag)",
    "openSubsonic": true
  }
}
"""

fun <T> StringSpecRootScope.expectEndpointResponse(
    methodName: String,
    returnDescription: String,
    expected: T,
    client: OpenSubsonicClient = mockClient,
    methodCall: suspend OpenSubsonicClient.() -> Result<T>,
) {
    "$methodName should return $returnDescription" {
        methodCall(client).shouldBeSuccess {
            it shouldBe expected
        }
    }
}

fun <T> StringSpecRootScope.expectEndpointResponse(
    methodName: String,
    returnDescription: String,
    expected: Throwable,
    client: OpenSubsonicClient = mockClient,
    methodCall: suspend OpenSubsonicClient.() -> Result<T>,
) {
    "$methodName should return $returnDescription" {
        methodCall(client).shouldBeFailure {
            it shouldBe expected
        }
    }
}

val expectedOpenSubsonicResponse = OpenSubsonicResponse(
    type = "AwesomeServerName",
    serverVersion = "0.1.3 (tag)",
    openSubsonic = true,
    status = StatusResponse.OK,
    version = "1.16.1"
)

fun Url.handlePath() = when (pathSegments.last()) {
    "star" -> handleParameters(
        parameters {
            append("albumId", "1")
        },
        SubsonicResponse
    )

    "unstar" -> handleParameters(
        parameters {
            append("albumId", "1")
        },
        SubsonicResponse
    )

    "setRating" -> handleParameters(
        parameters {
            append("id", "someId")
            append("rating", "1")
        },
        SubsonicResponse
    )

    "scrobble" -> handleParameters(
        parameters {
            append("id", "someId")
            append("submission", "true")
        },
        SubsonicResponse
    )

    "ping" -> SubsonicResponse
    "getLicense" -> GetLicenseResponse
    else -> genericError
}
