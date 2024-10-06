import io.kotest.core.spec.style.StringSpec

class AlbumSongListAPITest : StringSpec({
    include(albumSongListsApiTestFactory)
})
class MediaAnnotationAPITest : StringSpec({
    include(mediaAnnotationApiTestFactory)
})
class SystemAPITest : StringSpec({
    include(systemAPITestsFactory)
})
