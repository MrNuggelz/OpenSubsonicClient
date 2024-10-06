package io.github.mrnuggelz.opensubsonic.responses

import kotlinx.serialization.Serializable

@Serializable
public data class Song(
    val id: String,
    val parent: String? = null,
    val isDir: Boolean,
    val title: String,
    val album: String? = null,
    val artist: String? = null,
    val track: Int? = null,
    val year: Int? = null,
    val genre: String? = null,
    val coverArt: String? = null,
    val size: Int? = null,
    val contentType: String? = null,
    val suffix: String? = null,
    val transcodedContentType: String? = null,
    val transcodedSuffix: String? = null,
    val duration: Int? = null,
    val bitRate: Int? = null,
    val bitDepth: Int? = null,
    val samplingRate: Int? = null,
    val channelCount: Int? = null,
    val path: String? = null,
    val isVideo: Boolean? = null,
    val userRating: Int? = null,
    val avarageRating: Float? = null,
    val playCount: Long? = null,
    val discNumber: Int? = null,
    val created: String? = null,
    val starred: String? = null,
    val albumId: String? = null,
    val artistId: String? = null,
    val type: String? = null,
    val mediaType: String? = null,
    val bookmarkPosition: Long? = null,
    val originalWidth: Int? = null,
    val originalHeight: Int? = null,
    val played: String? = null,
    val bpm: Int? = null,
    val comment: String? = null,
    val sortName: String? = null,
    val musicBrainzId: String? = null,
    val genres: List<ItemGenre> = emptyList(),
    val artists: List<ArtistID3> = emptyList(),
    val displayArtist: String? = null,
    val albumArtists: List<ArtistID3> = emptyList(),
    val displayAlbumArtist: String? = null,
    val contributors: List<Contributor> = emptyList(),
    val displayComposer: String? = null,
    val moods: List<String> = emptyList(),
    val replayGain: ReplayGain? = null,
)

@Serializable
public data class Contributor(
    val role: String,
    val subRole: String? = null,
    val artist: ArtistID3,
)

@Serializable
public data class ReplayGain(
    val trackGain: Float? = null,
    val albumGain: Float? = null,
    val trackPeak: Double? = null,
    val albumPeak: Double? = null,
    val baseGain: Float? = null,
    val fallbackGain: Float? = null,
)
