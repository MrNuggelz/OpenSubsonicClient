package io.github.mrnuggelz.opensubsonic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

public suspend fun OpenSubsonicClient.internetRadioStations(): Result<List<InternetRadioStation>> =
    openSubsonicRequest<InternetRadioStations>("getInternetRadioStations", "internetRadioStations").map { it.stations }

public suspend fun OpenSubsonicClient.createInternetRadioStation(
    streamUrl: String,
    name: String,
    homepageUrl: String? = null,
): Result<OpenSubsonicResponse> = openSubsonicRequest("createInternetRadioStation") {
    parameter("streamUrl", streamUrl)
    parameter("name", name)
    parameter("homepageUrl", homepageUrl)
}

public suspend fun OpenSubsonicClient.updateInternetRadioStation(
    id: InternetRadioStationId,
    streamUrl: String,
    name: String,
    homepageUrl: String? = null,
): Result<OpenSubsonicResponse> = openSubsonicRequest("updateInternetRadioStation") {
    parameter("id", id)
    parameter("streamUrl", streamUrl)
    parameter("name", name)
    parameter("homepageUrl", homepageUrl)
}

public suspend fun OpenSubsonicClient.deleteInternetRadioStation(
    id: InternetRadioStationId
): Result<OpenSubsonicResponse> = openSubsonicRequest("deleteInternetRadioStation") { parameter("id", id) }

@Serializable
private data class InternetRadioStations(
    @SerialName("internetRadioStation")
    val stations: List<InternetRadioStation>
)

@Serializable
public data class InternetRadioStation(
    val id: InternetRadioStationId,
    val name: String,
    val streamUrl: String,
    val homePageUrl: String?,
)

@JvmInline
@Serializable
public value class InternetRadioStationId(public val value: String)
