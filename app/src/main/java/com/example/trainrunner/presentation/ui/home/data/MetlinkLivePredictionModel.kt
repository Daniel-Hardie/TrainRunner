package com.example.trainrunner.presentation.ui.home.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


// https://www.youtube.com/watch?v=bLIWWOMVxts
@JsonClass(generateAdapter = true)
data class MetlinkLivePredictionModel(
    @Json(name = "closed")
    val closed: Boolean = false,
    @Json(name = "departures")
    val departures: List<Departure> = emptyList(),
    @Json(name = "farezone")
    val farezone: String = ""
) {
    @JsonClass(generateAdapter = true)
    data class Departure(
        @Json(name = "arrival")
        val arrival: Arrival = Arrival(),
        @Json(name = "delay")
        val delay: String = "",
        @Json(name = "departure")
        val departure: Departure = Departure(),
        @Json(name = "destination")
        val destination: Destination = Destination(),
        @Json(name = "direction")
        val direction: String = "",
        @Json(name = "monitored")
        val monitored: Boolean = false,
        @Json(name = "name")
        val name: String = "",
        @Json(name = "operator")
        val operator: String = "",
        @Json(name = "origin")
        val origin: Origin = Origin(),
        @Json(name = "service_id")
        val serviceId: String = "",
        @Json(name = "status")
        val status: Any? = null,
        @Json(name = "stop_id")
        val stopId: String = "",
        @Json(name = "trip_id")
        val tripId: String = "",
        @Json(name = "vehicle_id")
        val vehicleId: Any? = null,
        @Json(name = "wheelchair_accessible")
        val wheelchairAccessible: Boolean = false
    ) {
        @JsonClass(generateAdapter = true)
        data class Arrival(
            @Json(name = "aimed")
            val aimed: String = "",
            @Json(name = "expected")
            val expected: Any? = null
        )

        @JsonClass(generateAdapter = true)
        data class Departure(
            @Json(name = "aimed")
            val aimed: String = "",
            @Json(name = "expected")
            val expected: Any? = null // this is the field we want
        )

        @JsonClass(generateAdapter = true)
        data class Destination(
            @Json(name = "name")
            val name: String = "",
            @Json(name = "stop_id")
            val stopId: String = ""
        )

        @JsonClass(generateAdapter = true)
        data class Origin(
            @Json(name = "name")
            val name: String = "",
            @Json(name = "stop_id")
            val stopId: String = ""
        )
    }
}