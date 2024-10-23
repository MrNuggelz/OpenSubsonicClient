package io.github.mrnuggelz.opensubsonic.requests

import io.github.mrnuggelz.opensubsonic.mockClientID3
import io.github.mrnuggelz.opensubsonic.mockClientNonID3
import io.kotest.core.spec.style.stringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize

val albumSongListsAPITestFactory = stringSpec {
    responseShouldBe(
        "ID3 - albumList",
        "album list",
        mockClientID3,
        { albumList(ListType.random) },
    ) { it shouldHaveSize 2 }

    responseShouldBe(
        "randomSongs",
        "album list",
        { randomSongs(1) }
    ) { resp ->
        resp.songs.map {
            it.id.value
        } shouldContainExactly listOf("300000060", "300000055")
    }

    responseShouldBe(
        "nowPlaying",
        "currently playing songs",
        { nowPlaying() }
    ) { it shouldHaveSize 1 }

    responseShouldBe(
        "songsByGenre",
        "electronic songs",
        { songsByGenre("Electronic", 1) }
    ) { it shouldHaveSize 1 }

    responseShouldBe(
        "NonID3 - getStarred",
        "starred albums, artists and songs",
        mockClientNonID3,
        { getStarred() }
    ) {
        it.album.shouldHaveSize(2)
        it.artist.shouldHaveSize(2)
        it.song.shouldHaveSize(2)
    }

    responseShouldBe(
        "ID3 - getStarred",
        "starred albums, artists and songs",
        mockClientID3,
        methodCall = { getStarred() }
    ) {
        it.album.shouldHaveSize(2)
        it.artist.shouldHaveSize(2)
        it.song.shouldHaveSize(2)
    }
}
