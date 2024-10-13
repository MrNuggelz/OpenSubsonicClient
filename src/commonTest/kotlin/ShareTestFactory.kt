import io.github.mrnuggelz.opensubsonic.createShare
import io.github.mrnuggelz.opensubsonic.deleteShare
import io.github.mrnuggelz.opensubsonic.shares
import io.github.mrnuggelz.opensubsonic.updateShare
import io.kotest.core.spec.style.stringSpec
import responseexpectations.expectedShares

val shareAPITestFactory = stringSpec {
    expectResponse("shares", "existing shares", expectedShares) { shares() }
    expectResponse("createShare", "existing shares", expectedShares) { createShare("shareId") }

    expectResponse("updateShare", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        updateShare("shareId")
    }

    expectResponse("deleteShare", "OpensubsonicResponse", expectedOpenSubsonicResponse) {
        deleteShare("shareId")
    }
}
