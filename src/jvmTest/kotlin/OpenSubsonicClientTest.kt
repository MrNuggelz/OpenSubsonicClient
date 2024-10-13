import io.kotest.core.spec.style.StringSpec

class AlbumSongListAPITest : StringSpec({
    include(albumSongListsAPITestFactory)
})
class BrowsingAPITest : StringSpec({
    include(browsingAPITestFactory)
})
class MediaAnnotationAPITest : StringSpec({
    include(mediaAnnotationAPITestFactory)
})
class SystemAPITest : StringSpec({
    include(systemAPITestFactory)
})
class SearchingAPITest : StringSpec({
    include(searchingAPITestFactory)
})
class ShareAPITest : StringSpec({
    include(shareAPITestFactory)
})
class PlaylistAPITest : StringSpec({
    include(playlistAPITestFactory)
})
class InternetRadioStationAPITest : StringSpec({
    include(internetRadioStationAPITestFactory)
})
class ChatAPITest : StringSpec({
    include(chatAPITestFactory)
})
class BookmarksAPITest : StringSpec({
    include(bookmarkAPITestFactory)
})
class MediaLibraryScanningAPITest : StringSpec({
    include(mediaLibraryScanningAPITestFactory)
})
