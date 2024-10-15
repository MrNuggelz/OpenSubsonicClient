import io.github.mrnuggelz.opensubsonic.PlaylistId
import io.github.mrnuggelz.opensubsonic.createPlaylist
import io.github.mrnuggelz.opensubsonic.deletePlaylist
import io.github.mrnuggelz.opensubsonic.playlist
import io.github.mrnuggelz.opensubsonic.playlists
import io.github.mrnuggelz.opensubsonic.responses.SongId
import io.github.mrnuggelz.opensubsonic.updatePlaylist
import io.kotest.core.spec.style.stringSpec
import responseexpectations.expectedPlaylist
import responseexpectations.expectedPlaylists

val playlistAPITestFactory = stringSpec {
    expectResponse("getPlaylists", "existing playlists for user", expectedPlaylists) { playlists("someUser") }
    expectResponse("getPlaylist", "the playlist", expectedPlaylist) { playlist(PlaylistId("playlistId")) }
    expectResponse("createPlaylist", "the playlist", expectedPlaylist) {
        createPlaylist("playlist name", listOf("songId1", "songId2").map { SongId(it) })
    }
    expectResponse("updatePlaylist", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        updatePlaylist(
            PlaylistId("playlistId"),
            "new playlist name",
            "some comment",
            true,
            SongId("songToAdd"),
            1,
        )
    }
    expectResponse("deletePlaylist", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        deletePlaylist(PlaylistId("playlistId"))
    }
}
