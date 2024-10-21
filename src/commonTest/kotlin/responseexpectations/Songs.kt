package responseexpectations

import io.github.mrnuggelz.opensubsonic.requests.CoverArtId
import io.github.mrnuggelz.opensubsonic.responses.AlbumId
import io.github.mrnuggelz.opensubsonic.responses.ArtistId
import io.github.mrnuggelz.opensubsonic.responses.Song
import io.github.mrnuggelz.opensubsonic.responses.SongId

val songPlanetaryPicknick = Song(
    id = SongId("300000121"),
    title = "Planetary Picknick",
    album = "Forget and Remember",
    artist = "Comfort Fit",
    track = 2,
    year = 2005,
    genre = "Hip-Hop",
    coverArt = CoverArtId("300000121"),
    size = 10715592,
    contentType = "audio/mpeg",
    suffix = "mp3",
    duration = 358,
    bitRate = 238,
    bitDepth = 16,
    samplingRate = 44100,
    channelCount = 2,
    path = "user/Comfort Fit/Forget And Remember/2 - Planetary Picknick.mp3",
    discNumber = 1,
    albumId = AlbumId("200000021"),
    artistId = ArtistId("100000036"),
    type = "music",
)

val songCanIHelpU = Song(
    id = SongId("300000116"),
    title = "Can I Help U?",
    album = "Forget and Remember",
    artist = "Comfort Fit",
    track = 1,
    year = 2005,
    genre = "Hip-Hop",
    coverArt = CoverArtId("300000116"),
    size = 2811819,
    contentType = "audio/mpeg",
    suffix = "mp3",
    duration = 103,
    bitRate = 216,
    bitDepth = 16,
    samplingRate = 44100,
    channelCount = 2,
    path = "user/Comfort Fit/Forget And Remember/1 - Can I Help U?.mp3",
    discNumber = 1,
    albumId = AlbumId("200000021"),
    artistId = ArtistId("100000036"),
    type = "music",
)

val songStayOutHere = Song(
    id = SongId("113bf5989ad15ce2cf1834ba9622983f"),
    title = "Stay Out Here",
    album = "Shaking The Habitual",
    artist = "The Knife",
    track = 11,
    year = 2013,
    genre = "Electronic",
    coverArt = CoverArtId("al-b87a936c682c49d4494c7ccb08c22d23_0"),
    size = 21096309,
    contentType = "audio/mp4",
    suffix = "m4a",
    duration = 642,
    bitRate = 257,
    bitDepth = 16,
    samplingRate = 44100,
    channelCount = 2,
    path = "The Knife/Shaking The Habitual/11 - Stay Out Here.m4a",
    created = "2023-03-13T16:30:35Z",
    albumId = AlbumId("b87a936c682c49d4494c7ccb08c22d23"),
    artistId = ArtistId("b29e9a9d780cb0e133f3add5662771b9"),
    type = "music",
    bookmarkPosition = 129000
)

val songIllwiththeSkills = Song(
    id = SongId("2b42782333450d02b177823e729664af"),
    title = "Ill with the Skills",
    album = "First Words",
    artist = "The Polish Ambassador",
    track = 17,
    year = 2014,
    coverArt = CoverArtId("mf-2b42782333450d02b177823e729664af_641edeb3"),
    size = 6219581,
    contentType = "audio/mpeg",
    suffix = "mp3",
    duration = 255,
    bitRate = 194,
    bitDepth = 16,
    samplingRate = 44100,
    channelCount = 2,
    path = "The Polish Ambassador/First Words/17 - Ill with the Skills.mp3",
    playCount = 1,
    played = "2023-03-15T15:23:37Z",
    created = "2023-03-25T11:44:51Z",
    albumId = AlbumId("dc8d8889a6fe08d8da7698c7ee1de61c"),
    artistId = ArtistId("64e1f796b283545d329cdf6a31a31dbe"),
    type = "music",
    bookmarkPosition = 7000
)
