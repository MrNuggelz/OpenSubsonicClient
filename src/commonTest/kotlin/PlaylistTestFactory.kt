import io.github.mrnuggelz.opensubsonic.createPlaylist
import io.github.mrnuggelz.opensubsonic.deletePlaylist
import io.github.mrnuggelz.opensubsonic.playlist
import io.github.mrnuggelz.opensubsonic.playlists
import io.github.mrnuggelz.opensubsonic.updatePlaylist
import io.kotest.core.spec.style.stringSpec
import responseexpectations.expectedPlaylist
import responseexpectations.expectedPlaylists

val playlistAPITestsFactory = stringSpec {
    expectResponse("getPlaylists", "existing playlists for user", expectedPlaylists) { playlists("someUser") }
    expectResponse("getPlaylist", "the playlist", expectedPlaylist) { playlist("playlistId") }
    expectResponse("createPlaylist", "the playlist", expectedPlaylist) {
        createPlaylist("playlist name", listOf("songId1", "songId2"))
    }
    expectResponse("updatePlaylist", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        updatePlaylist(
            "playlistId",
            "new playlist name",
            "some comment",
            true,
            "songToAdd",
            1
        )
    }
    expectResponse("deletePlaylist", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        deletePlaylist("playlistId")
    }
}
