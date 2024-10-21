package io.github.mrnuggelz.opensubsonic.requests

import io.github.mrnuggelz.opensubsonic.responses.AlbumId
import io.github.mrnuggelz.opensubsonic.responses.ArtistID3
import io.github.mrnuggelz.opensubsonic.responses.ArtistId
import io.github.mrnuggelz.opensubsonic.responses.ArtistIndexID3
import io.github.mrnuggelz.opensubsonic.responses.Artists
import io.github.mrnuggelz.opensubsonic.responses.Genre
import io.github.mrnuggelz.opensubsonic.responses.SongId
import io.kotest.core.spec.style.stringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import responseexpectations.expectedAlbum
import responseexpectations.expectedSong

val browsingAPITestFactory = stringSpec {
    expectResponse("album", "the album", expectedAlbum) {
        album(AlbumId("a70f5f4d781dfa00020e8023698318c0"))
    }
    expectResponse("song", "the song", expectedSong) {
        song(SongId("a70f5f4d781dfa00020e8023698318c0"))
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
                            id = ArtistId("100000016"),
                            name = "CARNÚN",
                            coverArt = CoverArtId("ar-100000016"),
                            albumCount = 1,
                        ),
                        ArtistID3(
                            id = ArtistId("100000027"),
                            name = "Chi.Otic",
                            coverArt = CoverArtId("ar-100000027"),
                            albumCount = 0,
                        )
                    )
                ),
                ArtistIndexID3(
                    name = "I",
                    artist = listOf(
                        ArtistID3(
                            id = ArtistId("100000013"),
                            name = "IOK-1",
                            coverArt = CoverArtId("ar-100000013"),
                            albumCount = 1,
                        )
                    )
                )
            )
        )
    ) { artists() }
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
    ) { genres() }
    responseShouldBe("artist", "the artist", methodCall = { artist(ArtistId("a70f5f4d781dfa00020e8023698318c0")) }) {
        it.id.value shouldBe "100000002"
    }
    responseShouldBe("artistInfo", "info of the artist", methodCall = { artistInfo(ArtistId("someId"), 2) }) {
        it.musicBrainzId shouldBe "1"
    }
    responseShouldBe("artistInfo2", "info of the artist", methodCall = { artistInfo2(ArtistId("someId"), 2) }) {
        it.musicBrainzId shouldBe "1"
    }
    responseShouldBe(
        "albumInfo",
        "album info",
        methodCall = { albumInfo(AlbumId("a70f5f4d781dfa00020e8023698318c0")) }
    ) { it.musicBrainzId shouldBe "6e1d48f7-717c-416e-af35-5d2454a13af2" }
    responseShouldBe(
        "albumInfo2",
        "album info",
        methodCall = { albumInfo2(AlbumId("a70f5f4d781dfa00020e8023698318c0")) }
    ) { it.musicBrainzId shouldBe "6e1d48f7-717c-416e-af35-5d2454a13af2" }
    responseShouldBe("topSongs", "the top songs", methodCall = { topSongs("someArtist", 2) }) {
        it shouldHaveSize 2
    }
    responseShouldBe("similarSongs", "similar songs", methodCall = { similarSongs(SongId("someId"), 2) }) {
        it shouldHaveSize 2
    }
    responseShouldBe("similarSongs2", "similar songs", methodCall = { similarSongs2(ArtistId("someId"), 2) }) {
        it shouldHaveSize 2
    }
}
