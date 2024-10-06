import io.kotest.core.spec.style.StringSpec

class SystemAPITest : StringSpec({
    include(systemAPITestsFactory)
})
class MediaAnnotationAPITest : StringSpec({
    include(mediaAnnotationApiTestFactory)
})
