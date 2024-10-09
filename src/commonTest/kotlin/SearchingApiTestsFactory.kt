import io.github.mrnuggelz.opensubsonic.SearchResult3
import io.github.mrnuggelz.opensubsonic.responses.AlbumID3
import io.github.mrnuggelz.opensubsonic.responses.ArtistID3
import io.github.mrnuggelz.opensubsonic.responses.Song
import io.github.mrnuggelz.opensubsonic.search2
import io.github.mrnuggelz.opensubsonic.search3
import io.kotest.core.spec.style.stringSpec
import io.kotest.matchers.collections.shouldBeSingleton
import io.kotest.matchers.shouldBe
import kotlinx.datetime.Instant

val searchingApiTestsFactory = stringSpec {
    responseShouldBe(
        "search2",
        "expected album, artist and song",
        methodCall = { search2("2 Mello", 1, 0, 1, 0, 1) }
    ) { resp ->
        resp.artist.shouldBeSingleton {
            it.id shouldBe "37ec820ca7193e17040c98f7da7c4b51"
        }
        resp.album.shouldBeSingleton {
            it.id shouldBe "f845aa4599ac7deb04bee2b70120a6d2"
        }
        resp.song.shouldBeSingleton {
            it.id shouldBe "c66f6736a20cfe8006c0ba3d9b0b84a1"
        }
    }

    expectResponse(
        "search3",
        "expected album, artist and song",
        SearchResult3(
            listOf(
                ArtistID3(
                    id = "37ec820ca7193e17040c98f7da7c4b51",
                    name = "2 Mello",
                    coverArt = "ar-37ec820ca7193e17040c98f7da7c4b51_0",
                    artistImageUrl = "https://demo.org/image.jpg",
                    albumCount = 1,
                )
            ),
            listOf(
                AlbumID3(
                    id = "ad0f112b6dcf83de5e9cae85d07f0d35",
                    name = "8-bit lagerfeuer",
                    artist = "pornophonique",
                    artistId = "91c3901ac465b9efc439e4be4270c2b6",
                    coverArt = "al-ad0f112b6dcf83de5e9cae85d07f0d35_640a93a8",
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
                    id = "082f435a363c32c57d5edb6a678a28d4",
                    parent = "e8a0685e3f3ec6f251649af2b58b8617",
                    isDir = false,
                    title = "\"polar expedition\"",
                    album = "Live at The Casbah - 2005-04-29",
                    artist = "The New Deal",
                    track = 4,
                    year = 2005,
                    coverArt = "mf-082f435a363c32c57d5edb6a678a28d4_6410b3ce",
                    size = 19866778,
                    contentType = "audio/flac",
                    suffix = "flac",
                    duration = 178,
                    bitRate = 880,
                    bitDepth = 16,
                    samplingRate = 44100,
                    channelCount = 2,
                    path = "The New Deal/Live at The Casbah - 2005-04-29/04 - \"polar expedition\".flac",
                    isVideo = false,
                    playCount = 8,
                    discNumber = 1,
                    created = "2023-03-14T17:51:22.112827504Z",
                    starred = "2023-03-27T09:45:27Z",
                    albumId = "e8a0685e3f3ec6f251649af2b58b8617",
                    artistId = "97e0398acf63f9fb930d7d4ce209a52b",
                    type = "music",
                    played = "2023-03-26T22:27:46Z",
                )
            )
        )
    ) { search3("2 Mello", 1, 0, 1, 0, 1) }
}
