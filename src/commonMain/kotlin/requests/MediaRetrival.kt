package io.github.mrnuggelz.opensubsonic.requests

import io.github.mrnuggelz.opensubsonic.OpenSubsonicClient
import io.github.mrnuggelz.opensubsonic.parameter
import io.github.mrnuggelz.opensubsonic.responses.AlbumID3
import io.github.mrnuggelz.opensubsonic.responses.Song
import io.github.mrnuggelz.opensubsonic.responses.SongId
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline
import kotlin.time.Duration

/**
 * Streams a given media file. This call does not count as a play or increases the [Song.playCount]. You can use the [scrobble] method to indicate that a media is played ensuring proper data in all cases.
 * The server must support the Transcode Offet extension, for the timeOffset parameter to be allowed for music.
 *
 * @param id A string which uniquely identifies the file to stream. Obtained by calls to [Song.id].
 * @param maxBitRate (Since 1.2.0) If specified, the server will attempt to limit the bitrate to this value, in kilobits per second. If set to zero, no limit is imposed.
 * @param format (Since 1.6.0) Specifies the preferred target format (e.g., “mp3” or “flv”) in case there are multiple applicable transcodings. Starting with 1.9.0 you can use the special value “raw” to disable transcoding.
 * @param timeOffset By default only applicable to video streaming. If specified, start streaming at the given offset (in seconds) into the media. The Transcode Offet extension enables the parameter to music too.
 *
 */
public suspend fun OpenSubsonicClient.stream(
    id: SongId,
    maxBitRate: Int? = null,
    format: String? = null,
    timeOffset: Duration? = null,
): Result<ByteReadChannel> =
    binaryOpenSubsonicRequest("stream") {
        parameter("id", id)
        parameter("maxBitRate", maxBitRate)
        parameter("format", format)
        parameter("timeOffset", timeOffset?.inWholeSeconds)
    }

/**
 * Downloads a given media file. Similar to [stream], but this method returns the original media data without transcoding or downsampling.
 * @param id A string which uniquely identifies the file to stream. Obtained by calls to [Song.id].
 */
public suspend fun OpenSubsonicClient.download(id: SongId): Result<ByteReadChannel> =
    binaryOpenSubsonicRequest("download") { parameter("id", id) }

/**
 * Returns a cover art image.
 * @param id The coverArt ID. Returned by most entities likes [Song] or [AlbumID3]
 */
public suspend fun OpenSubsonicClient.coverArt(id: CoverArtId, size: Int? = null): Result<ByteReadChannel> =
    binaryOpenSubsonicRequest("getCoverArt") {
        parameter("id", id)
        parameter("size", size)
    }

/**
 * Searches for and returns lyrics for a given song.
 * @param artist The artist name.
 * @param title The song title
 */
public suspend fun OpenSubsonicClient.lyrics(artist: String?, title: String?): Result<Lyrics> =
    openSubsonicRequest("getLyrics", "lyrics") {
        parameter("artist", artist)
        parameter("title", title)
    }

/**
 * Returns the avatar (personal image) for a user specified by [username].
 */
public suspend fun OpenSubsonicClient.avatar(username: String): Result<ByteReadChannel> =
    binaryOpenSubsonicRequest("getAvatar") { parameter("username", username) }

@Serializable
public data class Lyrics(
    val value: String,
    val artist: String? = null,
    val title: String? = null,
)

@JvmInline
@Serializable
public value class CoverArtId(public val value: String)
