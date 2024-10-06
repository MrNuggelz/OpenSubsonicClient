package io.github.mrnuggelz.opensubsonic

import io.github.mrnuggelz.opensubsonic.responses.AlbumID3
import io.github.mrnuggelz.opensubsonic.responses.AlbumList
import io.github.mrnuggelz.opensubsonic.responses.AlbumList2
import io.github.mrnuggelz.opensubsonic.responses.NowPlaying
import io.github.mrnuggelz.opensubsonic.responses.NowPlayingSong
import io.github.mrnuggelz.opensubsonic.responses.RandomSongs
import io.github.mrnuggelz.opensubsonic.responses.Song
import io.github.mrnuggelz.opensubsonic.responses.Songs
import io.github.mrnuggelz.opensubsonic.responses.Starred
import io.github.mrnuggelz.opensubsonic.responses.Starred2

public suspend fun OpenSubsonicClient.albumList(
    type: ListType,
    size: Int = 10,
    offset: Int = 0,
    fromYear: Int? = null,
    toYear: Int? = null,
    genre: String? = null,
    musicFolderId: String? = null,
): Result<List<Song>> = openSubsonicRequest<AlbumList>("getAlbumList", "albumList") {
    parameter("type", type.name)
    parameter("size", size)
    parameter("offset", offset)
    parameter("fromYear", fromYear)
    parameter("toYear", toYear)
    parameter("genre", genre)
    parameter("musicFolderId", musicFolderId)
}.map { it.album }

public suspend fun OpenSubsonicClient.albumList2(
    type: ListType,
    size: Int = 10,
    offset: Int = 0,
    fromYear: Int? = null,
    toYear: Int? = null,
    genre: String? = null,
    musicFolderId: String? = null,
): Result<List<AlbumID3>> = openSubsonicRequest<AlbumList2>("getAlbumList2", "albumList2") {
    parameter("type", type.name)
    parameter("size", size)
    parameter("offset", offset)
    parameter("fromYear", fromYear)
    parameter("toYear", toYear)
    parameter("genre", genre)
    parameter("musicFolderId", musicFolderId)
}.map { it.album }

public suspend fun OpenSubsonicClient.getStarred(musicFolderId: String? = null): Result<Starred> =
    openSubsonicRequest<Starred>(
        "getStarred",
        "starred"
    ) { parameter("musicFolderId", musicFolderId) }

public suspend fun OpenSubsonicClient.getStarred2(musicFolderId: String? = null): Result<Starred2> =
    openSubsonicRequest<Starred2>(
        "getStarred2",
        "starred2"
    ) { parameter("musicFolderId", musicFolderId) }

public suspend fun OpenSubsonicClient.nowPlaying(): Result<List<NowPlayingSong>> =
    openSubsonicRequest<NowPlaying>("getNowPlaying", "nowPlaying").map { it.entries }

public suspend fun OpenSubsonicClient.randomSongs(
    size: Int? = null,
    genre: String? = null,
    fromYear: Int? = null,
    toYear: Int? = null,
    musicFolderId: String? = null,
): Result<RandomSongs> = openSubsonicRequest("getRandomSongs", "randomSongs") {
    parameter("size", size)
    parameter("genre", genre)
    parameter("fromYear", fromYear)
    parameter("toYear", toYear)
    parameter("musicFolderId", musicFolderId)
}

public suspend fun OpenSubsonicClient.songsByGenre(
    genre: String,
    count: Int = 10,
    offset: Int = 0,
    musicFolderId: String? = null,
): Result<List<Song>> = openSubsonicRequest<Songs>("getSongsByGenre", "songsByGenre") {
    parameter("genre", genre)
    parameter("count", count)
    parameter("offset", offset)
    parameter("musicFolderId", musicFolderId)
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
