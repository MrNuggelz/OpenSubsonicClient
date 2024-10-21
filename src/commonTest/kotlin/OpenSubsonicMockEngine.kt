package io.github.mrnuggelz.opensubsonic

import io.github.mrnuggelz.opensubsonic.responses.GetAlbumInfo2Response
import io.github.mrnuggelz.opensubsonic.responses.GetAlbumInfoResponse
import io.github.mrnuggelz.opensubsonic.responses.GetAlbumList2Response
import io.github.mrnuggelz.opensubsonic.responses.GetAlbumListResponse
import io.github.mrnuggelz.opensubsonic.responses.GetAlbumResponse
import io.github.mrnuggelz.opensubsonic.responses.GetArtistInfo2Response
import io.github.mrnuggelz.opensubsonic.responses.GetArtistInfoResponse
import io.github.mrnuggelz.opensubsonic.responses.GetArtistResponse
import io.github.mrnuggelz.opensubsonic.responses.GetArtistsResponse
import io.github.mrnuggelz.opensubsonic.responses.GetBookmarksResponse
import io.github.mrnuggelz.opensubsonic.responses.GetChatMessages
import io.github.mrnuggelz.opensubsonic.responses.GetGenresResponse
import io.github.mrnuggelz.opensubsonic.responses.GetIndexesResponse
import io.github.mrnuggelz.opensubsonic.responses.GetInternetRadioStations
import io.github.mrnuggelz.opensubsonic.responses.GetLicenseResponse
import io.github.mrnuggelz.opensubsonic.responses.GetLyricResponse
import io.github.mrnuggelz.opensubsonic.responses.GetMusicDirectoryResponse
import io.github.mrnuggelz.opensubsonic.responses.GetMusicFoldersResponse
import io.github.mrnuggelz.opensubsonic.responses.GetNowPlayingResponse
import io.github.mrnuggelz.opensubsonic.responses.GetPlaylistResponse
import io.github.mrnuggelz.opensubsonic.responses.GetPlaylistsResponse
import io.github.mrnuggelz.opensubsonic.responses.GetRandomSongsResponse
import io.github.mrnuggelz.opensubsonic.responses.GetScanStatusResponse
import io.github.mrnuggelz.opensubsonic.responses.GetSharesResponse
import io.github.mrnuggelz.opensubsonic.responses.GetSimilarSongs2Response
import io.github.mrnuggelz.opensubsonic.responses.GetSimilarSongsResponse
import io.github.mrnuggelz.opensubsonic.responses.GetSongResponse
import io.github.mrnuggelz.opensubsonic.responses.GetSongsByGenreResponse
import io.github.mrnuggelz.opensubsonic.responses.GetStarred2Response
import io.github.mrnuggelz.opensubsonic.responses.GetStarredResponse
import io.github.mrnuggelz.opensubsonic.responses.GetTopSongsResponse
import io.github.mrnuggelz.opensubsonic.responses.Search2Response
import io.github.mrnuggelz.opensubsonic.responses.Search3Response
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

val binaryEndpoints = listOf(
    "stream",
    "download",
    "getCoverArt",
    "getAvatar",
)

val OpenSubsonicMockEngine = MockEngine { request ->
    val body = if (request.url.parameters.validateDefaultParameters()) {
        if (!request.url.toString().startsWith("http://localhost/rest/")) {
            notFoundErrorResponse
        }
        if (request.url.segments.last() in binaryEndpoints) {
            request.url.handleBinaryPath()?.let {
                return@MockEngine respond(
                    content = it,
                    status = HttpStatusCode.OK,
                )
            }
            notFoundErrorResponse
        } else {
            request.url.handlePath()
        }
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

fun Url.handleParameters(expectedParameters: Parameters): ByteArray? =
    if (parameters.filter { name, _ -> name !in listOf("u", "c", "f", "v", "s", "t") } == expectedParameters) {
        "someData".encodeToByteArray()
    } else {
        null
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

@Suppress("LongMethod", "CyclomaticComplexMethod")
fun Url.handleBinaryPath(): ByteArray? = when (segments.last()) {
    "stream" -> handleParameters(
        parameters {
            append("id", "songId")
            append("maxBitRate", "0")
            append("format", "mp3")
            append("timeOffset", "2")
        }
    )

    "download" -> handleParameters(
        parameters {
            append("id", "songId")
        }
    )
    "getCoverArt" -> handleParameters(
        parameters {
            append("id", "coverArtId")
        }
    )
    "getAvatar" -> handleParameters(
        parameters {
            append("username", "guest")
        }
    )
    else -> genericError.encodeToByteArray()
}

@Suppress("LongMethod", "CyclomaticComplexMethod")
fun Url.handlePath() = when (segments.last()) {
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
        parameters { append("id", "songId") },
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

    "getPlaylists" -> handleParameters(
        parameters { append("username", "someUser") },
        GetPlaylistsResponse
    )

    "getPlaylist" -> handleParameters(
        parameters { append("id", "playlistId") },
        GetPlaylistResponse
    )

    "createPlaylist" -> handleParameters(
        parameters {
            append("name", "playlist name")
            appendAll("songId", listOf("songId1", "songId2"))
        },
        GetPlaylistResponse
    )

    "updatePlaylist" -> handleParameters(
        parameters {
            append("playlistId", "playlistId")
            append("playlistName", "new playlist name")
            append("comment", "some comment")
            append("public", "true")
            append("songIdToAdd", "songToAdd")
            append("songIndexToRemove", "1")
        },
        SubsonicResponse
    )

    "deletePlaylist" -> handleParameters(
        parameters { append("id", "playlistId") },
        SubsonicResponse
    )

    "getInternetRadioStations" -> GetInternetRadioStations
    "createInternetRadioStation" -> handleParameters(
        parameters {
            append("name", "4ZZZ Community Radio")
            append("streamUrl", "https://stream.4zzz.org.au:9200/4zzz")
            append("homepageUrl", "https://4zzzfm.org.au")
        },
        SubsonicResponse
    )

    "updateInternetRadioStation" -> handleParameters(
        parameters {
            append("id", "1")
            append("name", "4ZZZ Community Radio")
            append("streamUrl", "https://stream.4zzz.org.au:9200/4zzz")
            append("homepageUrl", "https://4zzzfm.org.au")
        },
        SubsonicResponse
    )

    "deleteInternetRadioStation" -> handleParameters(parameters { append("id", "1") }, SubsonicResponse)

    "getChatMessages" -> handleParameters(parameters { append("since", "1678935707000") }, GetChatMessages)
    "addChatMessage" -> handleParameters(parameters { append("message", "hello world!") }, SubsonicResponse)

    "getBookmarks" -> GetBookmarksResponse
    "createBookmark" -> handleParameters(
        parameters {
            append("id", "123")
            append("position", "7000")
            append("comment", "playSub bookmark")
        },
        SubsonicResponse
    )

    "deleteBookmark" -> handleParameters(parameters { append("id", "123") }, SubsonicResponse)
    "savePlayQueue" -> handleParameters(
        parameters {
            append("id", "123")
            append("current", "123")
            append("position", "7000")
        },
        SubsonicResponse
    )

    "getScanStatus" -> GetScanStatusResponse
    "startScan" -> GetScanStatusResponse
    "getLyrics" -> handleParameters(
        parameters {
            append("artist", "Metallica")
            append("title", "Blitzkrieg")
        },
        GetLyricResponse
    )

    "ping" -> SubsonicResponse
    "getLicense" -> GetLicenseResponse
    else -> genericError
}
