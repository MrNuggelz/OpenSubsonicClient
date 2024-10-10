package responseexpectations

import io.github.mrnuggelz.opensubsonic.responses.ItemGenre
import io.github.mrnuggelz.opensubsonic.responses.ReplayGain
import io.github.mrnuggelz.opensubsonic.responses.Song

internal val expectedSong = Song(
    id = "a70f5f4d781dfa00020e8023698318c0",
    parent = "7a234fa5fc021f53f96601568505447c",
    isDir = false,
    title = "Prayer For Rain Ft Ryan Herr & Lizzy Plotkin",
    album = "Pushing Through The Pavement",
    artist = "The Polish Ambassador",
    track = 3,
    year = 2014,
    genre = "Electronic",
    coverArt = "mf-a70f5f4d781dfa00020e8023698318c0_640a9366",
    size = 6932992,
    contentType = "audio/mpeg",
    suffix = "mp3",
    duration = 271,
    bitRate = 203,
    samplingRate = 44100,
    channelCount = 2,
    path = "The Polish Ambassador/Pushing Through The Pavement/03 - Prayer For Rain Ft Ryan Herr & Lizzy Plotkin.mp3",
    isVideo = false,
    userRating = 2,
    playCount = 49,
    created = "2023-03-10T02:18:29.29713925Z",
    albumId = "7a234fa5fc021f53f96601568505447c",
    artistId = "64e1f796b283545d329cdf6a31a31dbe",
    type = "music",
    mediaType = "song",
    played = "2024-09-24T17:22:09.299Z",
    bpm = 0,
    comment = "http://www.jamendo.com cc_standard",
    sortName = "",
    musicBrainzId = "",
    genres = listOf(ItemGenre(name = "Electronic")),
    replayGain = ReplayGain(
        trackPeak = 1.0,
        albumPeak = 1.0,
    )
)
