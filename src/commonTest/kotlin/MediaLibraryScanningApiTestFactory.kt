import io.github.mrnuggelz.opensubsonic.ScanStatus
import io.github.mrnuggelz.opensubsonic.scanStatus
import io.github.mrnuggelz.opensubsonic.startScan
import io.kotest.core.spec.style.stringSpec

val mediaLibraryScanningAPITestFactory = stringSpec {
    expectResponse("scanStatus", "scan status", ScanStatus(true, 1)) {
        scanStatus()
    }
    expectResponse("startScan", "scan status", ScanStatus(true, 1)) {
        startScan()
    }
}
