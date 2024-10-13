package responseexpectations

import io.github.mrnuggelz.opensubsonic.responses.Album

internal val expectedAlbum = Album(
    id = "200000021",
    name = "Forget and Remember",
    artist = "Comfort Fit",
    artistId = "100000036",
    coverArt = "al-200000021",
    songCount = 20,
    duration = 4248,
    playCount = 0,
    created = "2021-07-22T02:09:31+00:00",
    starred = null,
    year = 2005,
    genre = "Hip-Hop",
    songs = listOf(songCanIHelpU, songPlanetaryPicknick)
)
