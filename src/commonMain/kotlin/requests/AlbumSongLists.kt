package io.github.mrnuggelz.opensubsonic.requests

import io.github.mrnuggelz.opensubsonic.OpenSubsonicClient
import io.github.mrnuggelz.opensubsonic.OpenSubsonicClientID3
import io.github.mrnuggelz.opensubsonic.OpenSubsonicClientNonID3
import io.github.mrnuggelz.opensubsonic.parameter
import io.github.mrnuggelz.opensubsonic.responses.AlbumID3
import io.github.mrnuggelz.opensubsonic.responses.AlbumList2
import io.github.mrnuggelz.opensubsonic.responses.NowPlaying
import io.github.mrnuggelz.opensubsonic.responses.NowPlayingSong
import io.github.mrnuggelz.opensubsonic.responses.RandomSongs
import io.github.mrnuggelz.opensubsonic.responses.Song
import io.github.mrnuggelz.opensubsonic.responses.Songs
import io.github.mrnuggelz.opensubsonic.responses.Starred
import io.github.mrnuggelz.opensubsonic.responses.Starred2

public suspend fun OpenSubsonicClientID3.albumList(
    type: ListType,
    size: Int = 10,
    offset: Int = 0,
    fromYear: Int? = null,
    toYear: Int? = null,
    genre: String? = null,
): Result<List<AlbumID3>> = openSubsonicRequest<AlbumList2>("getAlbumList2", "albumList2") {
    parameter("type", type.name)
    parameter("size", size)
    parameter("offset", offset)
    parameter("fromYear", fromYear)
    parameter("toYear", toYear)
    parameter("genre", genre)
}.map { it.album }

/**
 * Returns starred songs, albums and artists.
 */
public suspend fun OpenSubsonicClientNonID3.getStarred(): Result<Starred> =
    openSubsonicRequest<Starred>("getStarred", "starred")

/**
 * Returns starred songs, albums and artists.
 */
public suspend fun OpenSubsonicClientID3.getStarred(): Result<Starred2> =
    openSubsonicRequest<Starred2>("getStarred2", "starred2")

/**
 * Returns what is currently being played by all users.
 */
public suspend fun OpenSubsonicClient.nowPlaying(): Result<List<NowPlayingSong>> =
    openSubsonicRequest<NowPlaying>("getNowPlaying", "nowPlaying").map { it.entries }

/**
 * Returns random songs matching the given criteria.
 * @param size  The maximum number of songs to return. Max 500.
 * @param genre Only returns songs belonging to this genre.
 * @param fromYear Only return songs published after or in this year.
 * @param toYear Only return songs published before or in this year.
 */
public suspend fun OpenSubsonicClient.randomSongs(
    size: Int? = null,
    genre: String? = null,
    fromYear: Int? = null,
    toYear: Int? = null,
): Result<RandomSongs> = openSubsonicRequest("getRandomSongs", "randomSongs") {
    parameter("size", size)
    parameter("genre", genre)
    parameter("fromYear", fromYear)
    parameter("toYear", toYear)
}

public suspend fun OpenSubsonicClient.songsByGenre(
    genre: String,
    count: Int = 10,
    offset: Int = 0,
): Result<List<Song>> = openSubsonicRequest<Songs>("getSongsByGenre", "songsByGenre") {
    parameter("genre", genre)
    parameter("count", count)
    parameter("offset", offset)
}.map { it.song }

@Suppress("EnumEntryName", "EnumEntryNameCase", "EnumNaming")
public enum class ListType {
    random,
    newest,
    highest,
    frequent,
    recent,
    alphabeticalByName,
    alphabeticalByArtist,
    starred,
    byYear,
    byGenre,
}
