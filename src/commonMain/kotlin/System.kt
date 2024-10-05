package io.github.mrnuggelz.opensubsonic

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

public suspend fun OpenSubsonicClient.ping(): Result<OpenSubsonicResponse> =
    openSubsonicRequest("ping", null)

public suspend fun OpenSubsonicClient.license(): Result<License> =
    openSubsonicRequest<License>("getLicense", "license")

@Serializable
public data class License(
    val valid: Boolean,
    val email: String? = null,
    val licenseExpires: Instant? = null,
    val trialExpires: Instant? = null,
)
