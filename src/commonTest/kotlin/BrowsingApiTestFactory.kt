import io.github.mrnuggelz.opensubsonic.album
import io.github.mrnuggelz.opensubsonic.albumInfo
import io.github.mrnuggelz.opensubsonic.albumInfo2
import io.github.mrnuggelz.opensubsonic.artist
import io.github.mrnuggelz.opensubsonic.artistInfo
import io.github.mrnuggelz.opensubsonic.artistInfo2
import io.github.mrnuggelz.opensubsonic.artists
import io.github.mrnuggelz.opensubsonic.genres
import io.github.mrnuggelz.opensubsonic.indexes
import io.github.mrnuggelz.opensubsonic.musicDirectory
import io.github.mrnuggelz.opensubsonic.musicFolders
import io.github.mrnuggelz.opensubsonic.responses.ArtistID3
import io.github.mrnuggelz.opensubsonic.responses.ArtistIndexID3
import io.github.mrnuggelz.opensubsonic.responses.Artists
import io.github.mrnuggelz.opensubsonic.responses.Genre
import io.github.mrnuggelz.opensubsonic.responses.Indexes
import io.github.mrnuggelz.opensubsonic.responses.MusicFolder
import io.github.mrnuggelz.opensubsonic.similarSongs
import io.github.mrnuggelz.opensubsonic.similarSongs2
import io.github.mrnuggelz.opensubsonic.song
import io.github.mrnuggelz.opensubsonic.topSongs
import io.kotest.core.spec.style.stringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import responseexpectations.expectedAlbum
import responseexpectations.expectedSong

val browsingApiTestFactory = stringSpec {
    expectResponse("album", "the album", expectedAlbum) {
        album("a70f5f4d781dfa00020e8023698318c0")
    }
    expectResponse("song", "the song", expectedSong) {
        song("a70f5f4d781dfa00020e8023698318c0")
    }
    expectResponse(
        "artists",
        "artists",
        Artists(
            ignoredArticles = "The An A Die Das Ein Eine Les Le La",
            index = listOf(
                ArtistIndexID3(
                    name = "C",
                    artist = listOf(
                        ArtistID3(
                            id = "100000016",
                            name = "CARNÚN",
                            coverArt = "ar-100000016",
                            albumCount = 1,
                        ),
                        ArtistID3(
                            id = "100000027",
                            name = "Chi.Otic",
                            coverArt = "ar-100000027",
                            albumCount = 0,
                        )
                    )
                ),
                ArtistIndexID3(
                    name = "I",
                    artist = listOf(
                        ArtistID3(
                            id = "100000013",
                            name = "IOK-1",
                            coverArt = "ar-100000013",
                            albumCount = 1,
                        )
                    )
                )
            )
        )
    ) {
        artists()
    }
    expectResponse(
        "genres",
        "all genres",
        listOf(
            Genre(value = "Punk", songCount = 1, albumCount = 1),
            Genre(value = "Dark Ambient", songCount = 4, albumCount = 1),
            Genre(value = "Noise", songCount = 6, albumCount = 1),
            Genre(value = "Electronica", songCount = 11, albumCount = 1),
            Genre(value = "Dance", songCount = 11, albumCount = 1),
            Genre(value = "Electronic", songCount = 12, albumCount = 1),
            Genre(value = "Hip-Hop", songCount = 20, albumCount = 1)
        )
    ) {
        genres()
    }
    expectResponse(
        "musicFolders",
        "all music folders",
        listOf(
            MusicFolder("1", "music"),
            MusicFolder("4", "upload")
        )
    ) {
        musicFolders()
    }
    expectResponse(
        "getIndexes",
        "the indexes",
        Indexes(
            ignoredArticles = "The An A Die Das Ein Eine Les Le La",
            indexes = listOf(
                ArtistIndexID3(
                    name = "C",
                    artist = listOf(
                        ArtistID3(id = "100000016", name = "CARNÚN", coverArt = "ar-100000016", albumCount = 1),
                        ArtistID3(id = "100000027", name = "Chi.Otic", coverArt = "ar-100000027", albumCount = 0)
                    )
                ),
                ArtistIndexID3(
                    name = "I",
                    artist = listOf(
                        ArtistID3(id = "100000013", name = "IOK-1", coverArt = "ar-100000013", albumCount = 1)
                    )
                )
            )
        )
    ) { indexes("someFolder") }
    responseShouldBe(
        "musicDirectory",
        "the directory",
        methodCall = { musicDirectory("a70f5f4d781dfa00020e8023698318c0") }
    ) {
        it.id shouldBe "1"
    }
    responseShouldBe("artist", "the artist", methodCall = { artist("a70f5f4d781dfa00020e8023698318c0") }) {
        it.id shouldBe "100000002"
    }
    responseShouldBe("artistInfo", "info of the artist", methodCall = { artistInfo("someId", 2) }) {
        it.musicBrainzId shouldBe "1"
    }
    responseShouldBe("artistInfo2", "info of the artist", methodCall = { artistInfo2("someId", 2) }) {
        it.musicBrainzId shouldBe "1"
    }
    responseShouldBe("albumInfo", "album info", methodCall = { albumInfo("a70f5f4d781dfa00020e8023698318c0") }) {
        it.musicBrainzId shouldBe "6e1d48f7-717c-416e-af35-5d2454a13af2"
    }
    responseShouldBe("albumInfo2", "album info", methodCall = { albumInfo2("a70f5f4d781dfa00020e8023698318c0") }) {
        it.musicBrainzId shouldBe "6e1d48f7-717c-416e-af35-5d2454a13af2"
    }
    responseShouldBe("topSongs", "the top songs", methodCall = { topSongs("someArtist", 2) }) {
        it shouldHaveSize 2
    }
    responseShouldBe("similarSongs", "similar songs", methodCall = { similarSongs("someId", 2) }) {
        it shouldHaveSize 2
    }
    responseShouldBe("similarSongs2", "similar songs", methodCall = { similarSongs2("someId", 2) }) {
        it shouldHaveSize 2
    }
}
