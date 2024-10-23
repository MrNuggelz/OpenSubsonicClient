package io.github.mrnuggelz.opensubsonic.requests

import io.github.mrnuggelz.opensubsonic.mockClientID3
import io.github.mrnuggelz.opensubsonic.responses.AlbumID3
import io.github.mrnuggelz.opensubsonic.responses.AlbumId
import io.github.mrnuggelz.opensubsonic.responses.ArtistID3
import io.github.mrnuggelz.opensubsonic.responses.ArtistId
import io.github.mrnuggelz.opensubsonic.responses.Song
import io.github.mrnuggelz.opensubsonic.responses.SongId
import io.kotest.core.spec.style.stringSpec
import kotlinx.datetime.Instant

val searchingAPITestFactory = stringSpec {
    expectResponse(
        "ID3 - search",
        "expected album, artist and song",
        SearchResult(
            listOf(
                ArtistID3(
                    id = ArtistId("37ec820ca7193e17040c98f7da7c4b51"),
                    name = "2 Mello",
                    coverArt = CoverArtId("ar-37ec820ca7193e17040c98f7da7c4b51_0"),
                    artistImageUrl = "https://demo.org/image.jpg",
                    albumCount = 1,
                )
            ),
            listOf(
                AlbumID3(
                    id = AlbumId("ad0f112b6dcf83de5e9cae85d07f0d35"),
                    name = "8-bit lagerfeuer",
                    artist = "pornophonique",
                    artistId = ArtistId("91c3901ac465b9efc439e4be4270c2b6"),
                    coverArt = CoverArtId("al-ad0f112b6dcf83de5e9cae85d07f0d35_640a93a8"),
                    songCount = 8,
                    duration = 1954,
                    playCount = 97,
                    created = Instant.parse("2023-03-10T02:19:35.784818075Z"),
                    starred = Instant.parse("2023-03-22T01:51:06Z"),
                    year = 2007,
                )
            ),
            listOf(
                Song(
                    id = SongId("082f435a363c32c57d5edb6a678a28d4"),
                    title = "\"polar expedition\"",
                    album = "Live at The Casbah - 2005-04-29",
                    artist = "The New Deal",
                    track = 4,
                    year = 2005,
                    coverArt = CoverArtId("mf-082f435a363c32c57d5edb6a678a28d4_6410b3ce"),
                    size = 19866778,
                    contentType = "audio/flac",
                    suffix = "flac",
                    duration = 178,
                    bitRate = 880,
                    bitDepth = 16,
                    samplingRate = 44100,
                    channelCount = 2,
                    path = "The New Deal/Live at The Casbah - 2005-04-29/04 - \"polar expedition\".flac",
                    playCount = 8,
                    discNumber = 1,
                    created = "2023-03-14T17:51:22.112827504Z",
                    starred = "2023-03-27T09:45:27Z",
                    albumId = AlbumId("e8a0685e3f3ec6f251649af2b58b8617"),
                    artistId = ArtistId("97e0398acf63f9fb930d7d4ce209a52b"),
                    type = "music",
                    played = "2023-03-26T22:27:46Z",
                )
            )
        ),
        mockClientID3,
    ) { search("2 Mello", 1, 0, 1, 0, 1) }
}
