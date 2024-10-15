package io.github.mrnuggelz.opensubsonic

import io.github.mrnuggelz.opensubsonic.responses.AlbumID3
import io.github.mrnuggelz.opensubsonic.responses.ArtistID3
import io.github.mrnuggelz.opensubsonic.responses.Song
import kotlinx.serialization.Serializable

public suspend fun OpenSubsonicClient.search(
    query: String,
    artistCount: Int = 20,
    artistOffset: Int = 0,
    albumCount: Int = 20,
    albumOffset: Int = 0,
    songCount: Int = 20,
    songOffset: Int = 0,
): Result<SearchResult> = openSubsonicRequest("search3", "searchResult3") {
    append("query", query)
    parameter("artistCount", artistCount)
    parameter("artistOffset", artistOffset)
    parameter("albumCount", albumCount)
    parameter("albumOffset", albumOffset)
    parameter("songCount", songCount)
    parameter("songOffset", songOffset)
}

@Serializable
public data class SearchResult(
    val artist: List<ArtistID3> = emptyList(),
    val album: List<AlbumID3> = emptyList(),
    val song: List<Song> = emptyList(),
)
