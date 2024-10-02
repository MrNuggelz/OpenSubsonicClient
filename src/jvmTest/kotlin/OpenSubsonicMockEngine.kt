import io.github.mrnuggelz.opensubsonic.OpenSubsonicClient
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

fun Parameters.validateDefaultParameters() = filter { it, _ -> it in listOf("u", "c", "f", "v") } == parameters {
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

const val subsonicResponse = """
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

fun Url.handlePath() = when (pathSegments.last()) {
    "ping" -> subsonicResponse
    else -> genericError
}
