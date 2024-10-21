package responseexpectations

import io.github.mrnuggelz.opensubsonic.requests.Share
import io.github.mrnuggelz.opensubsonic.requests.ShareId
import kotlinx.datetime.Instant

val expectedShares = listOf(
    Share(
        id = ShareId("12"),
        url = "http://localhost:8989/share.php?id=12&secret=fXlKyEv3",
        description = "Forget and Remember (Comfort Fit)",
        username = "user",
        created = Instant.parse("2023-03-16T04:13:09+00:00"),
        visitCount = 0,
        songs = listOf(songCanIHelpU, songPlanetaryPicknick)
    )
)
