package com.example.trainrunner.presentation.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

// Handle custom data types: https://www.youtube.com/watch?v=-DYvjjn9lQ0&list=PLUPcj46QWTDWlxtIwE3A6VEWUFEO8nh0Z&index=4

@Entity(tableName = "route")
data class Route(
    @ColumnInfo(name = "routeId")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val uniqueValueForRouteNotification: Int = -1,
    val metlinkRouteId: String = "",
    val metlinkRouteShortName: String = "",
    val stationOneCode: String = "",
    val stationTwoCode: String = "",
    val toWellington: Boolean,
    val isActive: Boolean = true
)

@Entity(tableName = "routeNotification")
data class RouteNotification(
    @ColumnInfo(name = "routeNotificationId")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val routeUniqueId: Int = -1,
    val orderId: Int,
    val dayText: String,
    val dayShortText: String,
    val date: Date,
    val isActive: Boolean = true
)

// metlink properties populated from https://api.opendata.metlink.org.nz/v1/gtfs/stops?route_id=X
// Where X is route id
@Entity(tableName = "station")
data class Station(
    @ColumnInfo(name = "stationId")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val metlinkRouteId: String = "",
    val metlinkStopId: String = "",
    val metlinkStopCode: String = "",
    val metlinkStopName: String = "",
    val metlinkStopLatitude: Double = -1.00,
    val metlinkStopLongitude: Double = -1.00,
    val metlinkZoneId: Int = -1,
    val metlinkParentStation: String = ""
)

// Populated from https://api.opendata.metlink.org.nz/v1/gtfs/routes
// Where route_type = 2 (trains)
@Entity(tableName = "metlinkRoute")
data class MetlinkRoute(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val metlinkRouteId: String = "",
    val metlinkRouteShortName: String = "",
    val metlinkRouteLongName: String = "",
    val metlinkRouteDesc: String = ""
)

// Populated from https://backend.metlink.org.nz/api/v1/timetable
// With a JSON body of the following:
//{
//    "service": {serviceCode, e.g. "HVL"},
//    "start": "2024-04-02",
//    "end": "2024-04-02"
//}
@Entity(tableName = "metlinkSchedule")
data class MetlinkSchedule(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val parentStationCode: String = "",
    val departTime: String = "",
    val tripId: String = "",
    val orderId: Int = -1,
    val lineId: Int = -1,
    val toWellington: Boolean
)