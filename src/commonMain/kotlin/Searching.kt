package io.github.mrnuggelz.opensubsonic

import io.github.mrnuggelz.opensubsonic.responses.AlbumID3
import io.github.mrnuggelz.opensubsonic.responses.Artist
import io.github.mrnuggelz.opensubsonic.responses.ArtistID3
import io.github.mrnuggelz.opensubsonic.responses.Song
import kotlinx.serialization.Serializable

public suspend fun OpenSubsonicClient.search2(
    query: String,
    artistCount: Int = 20,
    artistOffset: Int = 0,
    albumCount: Int = 20,
    albumOffset: Int = 0,
    songCount: Int = 20,
    songOffset: Int = 0,
    musicFolderId: String? = null,
): Result<SearchResult2> = openSubsonicRequest("search2", "searchResult2") {
    append("query", query)
    append("artistCount", artistCount.toString())
    append("artistOffset", artistOffset.toString())
    append("albumCount", albumCount.toString())
    append("albumOffset", albumOffset.toString())
    append("songCount", songCount.toString())
    append("songOffset", songOffset.toString())
    parameter("musicFolderId", musicFolderId)
}

public suspend fun OpenSubsonicClient.search3(
    query: String,
    artistCount: Int = 20,
    artistOffset: Int = 0,
    albumCount: Int = 20,
    albumOffset: Int = 0,
    songCount: Int = 20,
    songOffset: Int = 0,
    musicFolderId: String? = null,
): Result<SearchResult3> = openSubsonicRequest("search3", "searchResult3") {
    append("query", query)
    parameter("artistCount", artistCount)
    parameter("artistOffset", artistOffset)
    parameter("albumCount", albumCount)
    parameter("albumOffset", albumOffset)
    parameter("songCount", songCount)
    parameter("songOffset", songOffset)
    parameter("musicFolderId", musicFolderId)
}

@Serializable
public data class SearchResult2(
    val artist: List<Artist> = emptyList(),
    val album: List<Song> = emptyList(),
    val song: List<Song> = emptyList(),
)

@Serializable
public data class SearchResult3(
    val artist: List<ArtistID3> = emptyList(),
    val album: List<AlbumID3> = emptyList(),
    val song: List<Song> = emptyList(),
)
