package com.christianfleschhut.firstappbasicactivityapi22.data

import com.squareup.moshi.Json

data class Stock(
    @Json(name = "firm_id") val firmId: Int,
    val open: Double,
    val high: Double,
    val low: Double,
    val close: Double,
    val change: Double
)