package io.github.mrnuggelz.opensubsonic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    id: String,
    streamUrl: String,
    name: String,
    homepageUrl: String? = null,
): Result<OpenSubsonicResponse> = openSubsonicRequest("updateInternetRadioStation") {
    parameter("id", id)
    parameter("streamUrl", streamUrl)
    parameter("name", name)
    parameter("homepageUrl", homepageUrl)
}

public suspend fun OpenSubsonicClient.deleteInternetRadioStation(id: String): Result<OpenSubsonicResponse> =
    openSubsonicRequest("deleteInternetRadioStation") { parameter("id", id) }

@Serializable
private data class InternetRadioStations(
    @SerialName("internetRadioStation")
    val stations: List<InternetRadioStation>
)

@Serializable
public data class InternetRadioStation(
    val id: String,
    val name: String,
    val streamUrl: String,
    val homePageUrl: String?,
)
