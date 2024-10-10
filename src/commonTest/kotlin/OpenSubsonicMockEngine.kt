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

fun <T> StringSpecRootScope.responseShouldBe(
    methodName: String,
    returnDescription: String,
    client: OpenSubsonicClient = mockClient,
    methodCall: suspend OpenSubsonicClient.() -> Result<T>,
    block: (T) -> Unit,
) {
    "$methodName should return $returnDescription" {
        methodCall(client).shouldBeSuccess(block)
    }
}

fun <T> StringSpecRootScope.expectResponse(
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

fun <T> StringSpecRootScope.expectResponse(
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

@Suppress("LongMethod", "CyclomaticComplexMethod")
fun Url.handlePath() = when (pathSegments.last()) {
    "getAlbumList" -> handleParameters(
        parameters {
            append("type", "random")
            append("size", "10")
            append("offset", "0")
        },
        GetAlbumListResponse
    )

    "getAlbumList2" -> handleParameters(
        parameters {
            append("type", "random")
            append("size", "10")
            append("offset", "0")
        },
        GetAlbumList2Response
    )

    "getRandomSongs" -> handleParameters(
        parameters {
            append("size", "1")
        },
        GetRandomSongsResponse
    )

    "getNowPlaying" -> GetNowPlayingResponse
    "getSongsByGenre" -> handleParameters(
        parameters {
            append("genre", "Electronic")
            append("count", "1")
            append("offset", "0")
        },
        GetSongsByGenreResponse
    )

    "getStarred" -> GetStarredResponse
    "getStarred2" -> GetStarred2Response
    "getAlbum" -> handleParameters(
        parameters {
            append("id", "a70f5f4d781dfa00020e8023698318c0")
        },
        GetAlbumResponse
    )

    "getSong" -> handleParameters(
        parameters {
            append("id", "a70f5f4d781dfa00020e8023698318c0")
        },
        GetSongResponse
    )

    "getArtists" -> GetArtistsResponse
    "getGenres" -> GetGenresResponse
    "getMusicFolders" -> GetMusicFoldersResponse
    "getIndexes" -> handleParameters(
        parameters {
            append("musicFolderId", "someFolder")
        },
        GetIndexesResponse
    )

    "getMusicDirectory" -> handleParameters(
        parameters {
            append("id", "a70f5f4d781dfa00020e8023698318c0")
        },
        GetMusicDirectoryResponse
    )

    "getArtist" -> handleParameters(
        parameters {
            append("id", "a70f5f4d781dfa00020e8023698318c0")
        },
        GetArtistResponse
    )

    "getArtistInfo" -> handleParameters(
        parameters {
            append("id", "someId")
            append("count", "2")
            append("includeNotPresent", "false")
        },
        GetArtistInfoResponse
    )

    "getArtistInfo2" -> handleParameters(
        parameters {
            append("id", "someId")
            append("count", "2")
            append("includeNotPresent", "false")
        },
        GetArtistInfo2Response
    )

    "getAlbumInfo" -> handleParameters(
        parameters {
            append("id", "a70f5f4d781dfa00020e8023698318c0")
        },
        GetAlbumInfoResponse
    )

    "getAlbumInfo2" -> handleParameters(
        parameters {
            append("id", "a70f5f4d781dfa00020e8023698318c0")
        },
        GetAlbumInfo2Response
    )

    "getTopSongs" -> handleParameters(
        parameters {
            append("artist", "someArtist")
            append("count", "2")
        },
        GetTopSongsResponse
    )

    "getSimilarSongs" -> handleParameters(
        parameters {
            append("id", "someId")
            append("count", "2")
        },
        GetSimilarSongsResponse
    )

    "getSimilarSongs2" -> handleParameters(
        parameters {
            append("id", "someId")
            append("count", "2")
        },
        GetSimilarSongs2Response
    )

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

    "search2" -> handleParameters(
        parameters {
            append("query", "2 Mello")
            append("artistCount", "1")
            append("artistOffset", "0")
            append("albumCount", "1")
            append("albumOffset", "0")
            append("songCount", "1")
            append("songOffset", "0")
        },
        Search2Response
    )

    "search3" -> handleParameters(
        parameters {
            append("query", "2 Mello")
            append("artistCount", "1")
            append("artistOffset", "0")
            append("albumCount", "1")
            append("albumOffset", "0")
            append("songCount", "1")
            append("songOffset", "0")
        },
        Search3Response
    )

    "getShares" -> GetSharesResponse
    "createShare" -> handleParameters(
        parameters { append("id", "shareId") },
        GetSharesResponse
    )

    "updateShare" -> handleParameters(
        parameters { append("id", "shareId") },
        SubsonicResponse
    )

    "deleteShare" -> handleParameters(
        parameters { append("id", "shareId") },
        SubsonicResponse
    )

    "ping" -> SubsonicResponse
    "getLicense" -> GetLicenseResponse
    else -> genericError
}
