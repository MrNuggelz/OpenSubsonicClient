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
