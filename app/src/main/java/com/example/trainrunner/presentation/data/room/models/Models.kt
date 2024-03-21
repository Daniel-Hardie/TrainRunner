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
    val trainLineId: Int = -1,
    val stationOneId: Int = -1,
    val stationTwoId: Int = -1,
    val stationOneCode: String = "",
    val stationTwoCode: String = "",
    val toWellington: Boolean,
    val isActive: Boolean = true,

)

@Entity(tableName = "routeNotification")
data class RouteNotification(
    @ColumnInfo(name = "routeNotificationId")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val routeId: Int = -1,
    val date: Date,
    val isActive: Boolean = true
)


@Entity(tableName = "station")
data class Station(
    @ColumnInfo(name = "stationId")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val trainLineId: Int = -1,
    val name: String = "",
    val code: String = ""
)