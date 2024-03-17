package com.example.trainrunner.presentation.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

// Handle custom data types: https://www.youtube.com/watch?v=-DYvjjn9lQ0&list=PLUPcj46QWTDWlxtIwE3A6VEWUFEO8nh0Z&index=4

@Entity(tableName = "stations")
data class Station(
    @ColumnInfo(name = "stationId")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val line: String,
    val codeName: String,
    val fullName: String
)

@Entity(tableName = "routes")
data class Route(
    @ColumnInfo(name = "routeId")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val initialStationIdFk: Int,
    val destinationStationIdFk: Int,
    val trackMonday: Boolean,
    val trackTuesday: Boolean,
    val trackWednesday: Boolean,
    val trackThursday: Boolean,
    val trackFriday: Boolean,
    val trackSaturday: Boolean,
    val trackSunday: Boolean,
    val time: Date,
    val isActive: Boolean
)