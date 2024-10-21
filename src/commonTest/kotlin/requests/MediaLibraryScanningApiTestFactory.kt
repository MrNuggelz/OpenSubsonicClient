package io.github.mrnuggelz.opensubsonic.requests

import io.kotest.core.spec.style.stringSpec

val mediaLibraryScanningAPITestFactory = stringSpec {
    expectResponse("scanStatus", "scan status", ScanStatus(true, 1)) {
        scanStatus()
    }
    expectResponse("startScan", "scan status", ScanStatus(true, 1)) {
        startScan()
    }
}
