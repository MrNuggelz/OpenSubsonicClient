package responseexpectations

import io.github.mrnuggelz.opensubsonic.Playlist
import io.github.mrnuggelz.opensubsonic.responses.PlaylistWithSongs
import io.github.mrnuggelz.opensubsonic.responses.Song
import kotlinx.datetime.Instant

val expectedPlaylists = listOf(
    Playlist(
        id = "800000003",
        name = "random - admin - private (admin)",
        owner = "admin",
        public = false,
        created = Instant.parse("2021-02-23T04:35:38+00:00"),
        changed = Instant.parse("2021-02-23T04:35:38+00:00"),
        songCount = 43,
        duration = 17875
    ),
    Playlist(
        id = "800000002",
        name = "random - admin - public (admin)",
        owner = "admin",
        public = true,
        created = Instant.parse("2021-02-23T04:34:56+00:00"),
        changed = Instant.parse("2021-02-23T04:34:56+00:00"),
        songCount = 43,
        duration = 17786
    )
)

val expectedPlaylist = PlaylistWithSongs(
    Playlist(
        id = "800000075",
        name = "testcreate",
        owner = "user",
        public = true,
        created = Instant.parse("2023-03-16T03:18:41+00:00"),
        changed = Instant.parse("2023-03-16T03:18:41+00:00"),
        songCount = 1,
        duration = 304,
    ),
    listOf(
        Song(
            id = "300000060",
            parent = "200000002",
            title = "BrownSmoke",
            isDir = false,
            isVideo = false,
            type = "music",
            albumId = "200000002",
            album = "Colorsmoke EP",
            artistId = "100000002",
            artist = "Synthetic",
            coverArt = "300000060",
            duration = 304,
            bitRate = 20,
            bitDepth = 16,
            samplingRate = 44100,
            channelCount = 2,
            userRating = 5,
            averageRating = 5f,
            track = 4,
            year = 2007,
            genre = "Electronic",
            size = 792375,
            discNumber = 1,
            suffix = "wma",
            contentType = "audio/x-ms-wma",
            path = "Synthetic/Synthetic_-_Colorsmoke_EP-20k217-2007/04-Synthetic_-_BrownSmokeYSBM20k22khS.wma"
        )
    )
)