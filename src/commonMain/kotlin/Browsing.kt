package io.github.mrnuggelz.opensubsonic

import io.github.mrnuggelz.opensubsonic.responses.Album
import io.github.mrnuggelz.opensubsonic.responses.AlbumInfo
import io.github.mrnuggelz.opensubsonic.responses.Artist
import io.github.mrnuggelz.opensubsonic.responses.ArtistInfo
import io.github.mrnuggelz.opensubsonic.responses.ArtistInfo2
import io.github.mrnuggelz.opensubsonic.responses.Artists
import io.github.mrnuggelz.opensubsonic.responses.Directory
import io.github.mrnuggelz.opensubsonic.responses.Genre
import io.github.mrnuggelz.opensubsonic.responses.Indexes
import io.github.mrnuggelz.opensubsonic.responses.MusicFolder
import io.github.mrnuggelz.opensubsonic.responses.Song
import io.github.mrnuggelz.opensubsonic.responses.Songs
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

public suspend fun OpenSubsonicClient.musicFolders(): Result<List<MusicFolder>> =
    openSubsonicRequest<MusicFolders>("getMusicFolders", "musicFolders").map { it.musicFolder }

public suspend fun OpenSubsonicClient.indexes(
    musicFolderId: String? = null,
    ifModifiedSince: Instant? = null,
): Result<Indexes> =
    openSubsonicRequest("getIndexes", "indexes") {
        parameter("musicFolderId", musicFolderId)
        parameter("ifModifiedSince", ifModifiedSince)
    }

public suspend fun OpenSubsonicClient.musicDirectory(id: String): Result<Directory> =
    openSubsonicRequest("getMusicDirectory", "directory") { append("id", id) }

public suspend fun OpenSubsonicClient.song(id: String): Result<Song> =
    openSubsonicRequest("getSong", "song") { append("id", id) }

public suspend fun OpenSubsonicClient.artists(): Result<Artists> =
    openSubsonicRequest("getArtists", "artists")

public suspend fun OpenSubsonicClient.artist(id: String): Result<Artist> =
    openSubsonicRequest("getArtist", "artist") { append("id", id) }

public suspend fun OpenSubsonicClient.album(id: String): Result<Album> =
    openSubsonicRequest("getAlbum", "album") { append("id", id) }

public suspend fun OpenSubsonicClient.genres(): Result<List<Genre>> =
    openSubsonicRequest<Genres>("getGenres", "genres").map { it.genre }

public suspend fun OpenSubsonicClient.artistInfo(
    id: String,
    count: Int = 20,
    includeNotPresent: Boolean = false,
): Result<ArtistInfo> =
    openSubsonicRequest("getArtistInfo", "artistInfo") {
        parameter("id", id)
        parameter("count", count)
        parameter("includeNotPresent", includeNotPresent)
    }

public suspend fun OpenSubsonicClient.artistInfo2(
    id: String,
    count: Int = 20,
    includeNotPresent: Boolean = false,
): Result<ArtistInfo2> =
    openSubsonicRequest("getArtistInfo2", "artistInfo2") {
        parameter("id", id)
        parameter("count", count)
        parameter("includeNotPresent", includeNotPresent)
    }

public suspend fun OpenSubsonicClient.albumInfo(id: String): Result<AlbumInfo> =
    openSubsonicRequest("getAlbumInfo", "albumInfo") { append("id", id) }

public suspend fun OpenSubsonicClient.albumInfo2(id: String): Result<AlbumInfo> =
    openSubsonicRequest("getAlbumInfo2", "albumInfo") { append("id", id) }

public suspend fun OpenSubsonicClient.topSongs(
    artist: String,
    count: Int = 50,
): Result<List<Song>> =
    openSubsonicRequest<Songs>("getTopSongs", "topSongs") {
        parameter("artist", artist)
        parameter("count", count)
    }.map { it.song }

public suspend fun OpenSubsonicClient.similarSongs(
    id: String,
    count: Int = 50,
): Result<List<Song>> =
    openSubsonicRequest<Songs>("getSimilarSongs", "similarSongs") {
        parameter("id", id)
        parameter("count", count)
    }.map { it.song }

public suspend fun OpenSubsonicClient.similarSongs2(
    id: String,
    count: Int = 50,
): Result<List<Song>> =
    openSubsonicRequest<Songs>("getSimilarSongs2", "similarSongs2") {
        parameter("id", id)
        parameter("count", count)
    }.map { it.song }

@Serializable
private data class MusicFolders(
    val musicFolder: List<MusicFolder>
)

@Serializable
private data class Genres(
    val genre: List<Genre>
)
