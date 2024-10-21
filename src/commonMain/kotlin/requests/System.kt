package io.github.mrnuggelz.opensubsonic.requests

import io.github.mrnuggelz.opensubsonic.OpenSubsonicClient
import io.github.mrnuggelz.opensubsonic.responses.OpenSubsonicResponse
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

/**
 * Used to test connectivity with the server.
 */
public suspend fun OpenSubsonicClient.ping(): Result<OpenSubsonicResponse> =
    openSubsonicRequest("ping", null)

/**
 * Get details about the software license.
 */
public suspend fun OpenSubsonicClient.license(): Result<License> =
    openSubsonicRequest<License>("getLicense", "license")

@Serializable
public data class License(
    val valid: Boolean,
    val email: String? = null,
    val licenseExpires: Instant? = null,
    val trialExpires: Instant? = null,
)
