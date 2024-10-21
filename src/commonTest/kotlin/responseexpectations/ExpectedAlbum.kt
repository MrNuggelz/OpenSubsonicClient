package responseexpectations

import io.github.mrnuggelz.opensubsonic.requests.CoverArtId
import io.github.mrnuggelz.opensubsonic.responses.Album
import io.github.mrnuggelz.opensubsonic.responses.AlbumId
import io.github.mrnuggelz.opensubsonic.responses.ArtistId

internal val expectedAlbum = Album(
    id = AlbumId("200000021"),
    name = "Forget and Remember",
    artist = "Comfort Fit",
    artistId = ArtistId("100000036"),
    coverArt = CoverArtId("al-200000021"),
    songCount = 20,
    duration = 4248,
    playCount = 0,
    created = "2021-07-22T02:09:31+00:00",
    starred = null,
    year = 2005,
    genre = "Hip-Hop",
    songs = listOf(songCanIHelpU, songPlanetaryPicknick)
)
