import io.github.mrnuggelz.opensubsonic.ListType
import io.github.mrnuggelz.opensubsonic.albumList
import io.github.mrnuggelz.opensubsonic.albumList2
import io.github.mrnuggelz.opensubsonic.getStarred
import io.github.mrnuggelz.opensubsonic.getStarred2
import io.github.mrnuggelz.opensubsonic.nowPlaying
import io.github.mrnuggelz.opensubsonic.randomSongs
import io.github.mrnuggelz.opensubsonic.songsByGenre
import io.kotest.core.spec.style.stringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize

val albumSongListsAPITestFactory = stringSpec {
    responseShouldBe(
        "albumList",
        "album list",
        methodCall = { albumList(ListType.random) }
    ) { it shouldHaveSize 2 }

    responseShouldBe(
        "albumList2",
        "album list",
        methodCall = { albumList2(ListType.random) }
    ) { it shouldHaveSize 2 }

    responseShouldBe(
        "randomSongs",
        "album list",
        methodCall = { randomSongs(1) }
    ) { resp ->
        resp.songs.map {
            it.id
        } shouldContainExactly listOf("300000060", "300000055")
    }

    responseShouldBe(
        "nowPlaying",
        "currently playing songs",
        methodCall = { nowPlaying() }
    ) { it shouldHaveSize 1 }

    responseShouldBe(
        "songsByGenre",
        "electronic songs",
        methodCall = { songsByGenre("Electronic", 1) }
    ) { it shouldHaveSize 1 }

    responseShouldBe(
        "getStarred",
        "starred albums, artists and songs",
        methodCall = { getStarred() }
    ) {
        it.album.shouldHaveSize(2)
        it.artist.shouldHaveSize(2)
        it.song.shouldHaveSize(2)
    }

    responseShouldBe(
        "getStarred2",
        "starred albums, artists and songs",
        methodCall = { getStarred2() }
    ) {
        it.album.shouldHaveSize(2)
        it.artist.shouldHaveSize(2)
        it.song.shouldHaveSize(2)
    }
}
