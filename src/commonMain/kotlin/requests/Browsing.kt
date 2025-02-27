package io.github.mrnuggelz.opensubsonic.requests

import io.github.mrnuggelz.opensubsonic.OpenSubsonicClient
import io.github.mrnuggelz.opensubsonic.OpenSubsonicClientID3
import io.github.mrnuggelz.opensubsonic.OpenSubsonicClientNonID3
import io.github.mrnuggelz.opensubsonic.parameter
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
import io.github.mrnuggelz.opensubsonic.responses.SongAlbumArtistId
import io.github.mrnuggelz.opensubsonic.responses.SongAlbumId
import io.github.mrnuggelz.opensubsonic.responses.SongId
import io.github.mrnuggelz.opensubsonic.responses.Songs
import kotlinx.serialization.Serializable

public suspend fun OpenSubsonicClient.song(id: SongId): Result<Song> =
    openSubsonicRequest("getSong", "song") { parameter("id", id) }

/**
 * Returns all artists
 */
public suspend fun OpenSubsonicClientID3.artists(): Result<Artists> =
    openSubsonicRequest("getArtists", "artists")

/**
 * Returns details for an artist, including a list of albums.
 */
public suspend fun OpenSubsonicClientID3.artist(id: ArtistId): Result<Artist> =
    openSubsonicRequest("getArtist", "artist") { parameter("id", id) }

/**
 * Returns details for an album, including a list of songs.
 */
public suspend fun OpenSubsonicClientID3.album(id: AlbumId): Result<Album> =
    openSubsonicRequest("getAlbum", "album") { parameter("id", id) }

public suspend fun OpenSubsonicClient.genres(): Result<List<Genre>> =
    openSubsonicRequest<Genres>("getGenres", "genres").map { it.genre }

/**
 * Returns artist info with biography, image URLs and similar artists, using data from last.fm.
 * @param id The artist, album or song ID.
 * @param includeNotPresent Whether to return artists that are not present in the media library.
 */
public suspend fun OpenSubsonicClientNonID3.artistInfo(
    id: SongAlbumArtistId,
    count: Int = 20,
    includeNotPresent: Boolean = false,
): Result<ArtistInfo> =
    openSubsonicRequest("getArtistInfo", "artistInfo") {
        parameter("id", id)
        parameter("count", count)
        parameter("includeNotPresent", includeNotPresent)
    }

/**
 * Returns artist info with biography, image URLs and similar artists, using data from last.fm.
 * @param id The artist, album or song ID.
 * @param includeNotPresent Whether to return artists that are not present in the media library.
 */
public suspend fun OpenSubsonicClientID3.artistInfo(
    id: SongAlbumArtistId,
    count: Int = 20,
    includeNotPresent: Boolean = false,
): Result<ArtistInfo2> =
    openSubsonicRequest("getArtistInfo2", "artistInfo2") {
        parameter("id", id)
        parameter("count", count)
        parameter("includeNotPresent", includeNotPresent)
    }

/**
 * Returns album notes, image URLs etc, using data from last.fm.
 * @param id The album ID or song ID
 */
public suspend fun OpenSubsonicClientNonID3.albumInfo(id: SongAlbumId): Result<AlbumInfo> =
    openSubsonicRequest("getAlbumInfo", "albumInfo") { parameter("id", id) }

/**
 * Returns album notes, image URLs etc, using data from last.fm.
 * @param id The album ID or song ID
 */
public suspend fun OpenSubsonicClientID3.albumInfo(id: SongAlbumId): Result<AlbumInfo> =
    openSubsonicRequest("getAlbumInfo2", "albumInfo") { parameter("id", id) }

/**
 * Returns top songs for the given artist, using data from last.fm.
 */
public suspend fun OpenSubsonicClient.topSongs(
    artist: String,
    count: Int = 50,
): Result<List<Song>> =
    openSubsonicRequest<Songs>("getTopSongs", "topSongs") {
        parameter("artist", artist)
        parameter("count", count)
    }.map { it.song }

/**
 * Returns a random collection of songs, limited by [count], from the given artist and similar artists, using data from last.fm.
 */
public suspend fun OpenSubsonicClientNonID3.similarSongs(
    id: SongAlbumArtistId,
    count: Int = 50,
): Result<List<Song>> =
    openSubsonicRequest<Songs>("getSimilarSongs", "similarSongs") {
        parameter("id", id)
        parameter("count", count)
    }.map { it.song }

/**
 * Returns a random collection of songs, limited by [count], from the given artist and similar artists, using data from last.fm.
 */
public suspend fun OpenSubsonicClientID3.similarSongs(
    id: SongAlbumArtistId,
    count: Int = 50,
): Result<List<Song>> =
    openSubsonicRequest<Songs>("getSimilarSongs2", "similarSongs2") {
        parameter("id", id)
        parameter("count", count)
    }.map { it.song }

@Serializable
private data class Genres(val genre: List<Genre>)
