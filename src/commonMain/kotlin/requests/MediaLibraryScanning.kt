package io.github.mrnuggelz.opensubsonic.requests

import io.github.mrnuggelz.opensubsonic.OpenSubsonicClient
import kotlinx.serialization.Serializable

public suspend fun OpenSubsonicClient.scanStatus(): Result<ScanStatus> =
    openSubsonicRequest("getScanStatus", "scanStatus")

public suspend fun OpenSubsonicClient.startScan(): Result<ScanStatus> =
    openSubsonicRequest("startScan", "scanStatus")

@Serializable
public data class ScanStatus(
    val scanning: Boolean,
    val count: Int? = null,
)
