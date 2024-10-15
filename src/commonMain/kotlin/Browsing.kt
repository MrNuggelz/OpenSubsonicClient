package io.github.mrnuggelz.opensubsonic

import io.github.mrnuggelz.opensubsonic.responses.Album
import io.github.mrnuggelz.opensubsonic.responses.AlbumId
import io.github.mrnuggelz.opensubsonic.responses.AlbumInfo
import io.github.mrnuggelz.opensubsonic.responses.Artist
import io.github.mrnuggelz.opensubsonic.responses.ArtistId
import io.github.mrnuggelz.opensubsonic.responses.ArtistInfo
import io.github.mrnuggelz.opensubsonic.responses.ArtistInfo2
import io.github.mrnuggelz.opensubsonic.responses.Artists
import io.github.mrnuggelz.opensubsonic.responses.Genre
import io.github.mrnuggelz.opensubsonic.responses.Song
import io.github.mrnuggelz.opensubsonic.responses.SongId
import io.github.mrnuggelz.opensubsonic.responses.Songs
import kotlinx.serialization.Serializable

public suspend fun OpenSubsonicClient.song(id: SongId): Result<Song> =
    openSubsonicRequest("getSong", "song") { parameter("id", id) }

public suspend fun OpenSubsonicClient.artists(): Result<Artists> =
    openSubsonicRequest("getArtists", "artists")

public suspend fun OpenSubsonicClient.artist(id: ArtistId): Result<Artist> =
    openSubsonicRequest("getArtist", "artist") { parameter("id", id) }

public suspend fun OpenSubsonicClient.album(id: AlbumId): Result<Album> =
    openSubsonicRequest("getAlbum", "album") { parameter("id", id) }

public suspend fun OpenSubsonicClient.genres(): Result<List<Genre>> =
    openSubsonicRequest<Genres>("getGenres", "genres").map { it.genre }

public suspend fun OpenSubsonicClient.artistInfo(
    id: SongAlbumArtistId,
    count: Int = 20,
    includeNotPresent: Boolean = false,
): Result<ArtistInfo> =
    openSubsonicRequest("getArtistInfo", "artistInfo") {
        parameter("id", id)
        parameter("count", count)
        parameter("includeNotPresent", includeNotPresent)
    }

public suspend fun OpenSubsonicClient.artistInfo2(
    id: SongAlbumArtistId,
    count: Int = 20,
    includeNotPresent: Boolean = false,
): Result<ArtistInfo2> =
    openSubsonicRequest("getArtistInfo2", "artistInfo2") {
        parameter("id", id)
        parameter("count", count)
        parameter("includeNotPresent", includeNotPresent)
    }

public suspend fun OpenSubsonicClient.albumInfo(id: SongAlbumId): Result<AlbumInfo> =
    openSubsonicRequest("getAlbumInfo", "albumInfo") { parameter("id", id) }

public suspend fun OpenSubsonicClient.albumInfo2(id: SongAlbumId): Result<AlbumInfo> =
    openSubsonicRequest("getAlbumInfo2", "albumInfo") { parameter("id", id) }

public suspend fun OpenSubsonicClient.topSongs(
    artist: String,
    count: Int = 50,
): Result<List<Song>> =
    openSubsonicRequest<Songs>("getTopSongs", "topSongs") {
        parameter("artist", artist)
        parameter("count", count)
    }.map { it.song }

public suspend fun OpenSubsonicClient.similarSongs(
    id: SongAlbumArtistId,
    count: Int = 50,
): Result<List<Song>> =
    openSubsonicRequest<Songs>("getSimilarSongs", "similarSongs") {
        parameter("id", id)
        parameter("count", count)
    }.map { it.song }

public suspend fun OpenSubsonicClient.similarSongs2(
    id: SongAlbumArtistId,
    count: Int = 50,
): Result<List<Song>> =
    openSubsonicRequest<Songs>("getSimilarSongs2", "similarSongs2") {
        parameter("id", id)
        parameter("count", count)
    }.map { it.song }

@Serializable
private data class Genres(
    val genre: List<Genre>
)
