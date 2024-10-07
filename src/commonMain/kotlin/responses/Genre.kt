package io.github.mrnuggelz.opensubsonic.responses

import kotlinx.serialization.Serializable

@Serializable
public data class Genre(
    val value: String,
    val songCount: Int,
    val albumCount: Int,
)
