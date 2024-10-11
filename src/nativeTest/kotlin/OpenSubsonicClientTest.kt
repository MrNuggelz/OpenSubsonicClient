import io.kotest.core.spec.style.StringSpec

class AlbumSongListAPITest : StringSpec({
    include(albumSongListsApiTestFactory)
})
class BrowsingAPITest : StringSpec({
    include(browsingApiTestFactory)
})
class MediaAnnotationAPITest : StringSpec({
    include(mediaAnnotationApiTestFactory)
})
class SystemAPITest : StringSpec({
    include(systemAPITestsFactory)
})
class SearchingAPITest : StringSpec({
    include(searchingApiTestsFactory)
})
class ShareAPITest : StringSpec({
    include(shareAPITestsFactory)
})
class PlaylistAPITest : StringSpec({
    include(playlistAPITestsFactory)
})
class InternetRadioStationAPITest : StringSpec({
    include(internetRadioStationApiTestFactory)
})
