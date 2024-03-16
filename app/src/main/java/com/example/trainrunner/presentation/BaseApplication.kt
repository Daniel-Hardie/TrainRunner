package com.example.trainrunner.presentation

import android.app.Application
import com.example.trainrunner.data.HuttValleyStationsSource
import com.example.trainrunner.data.StationRepository

class BaseApplication : Application() {
    // Loads the database
    private val huttValleyLocalDataSource by lazy {
        HuttValleyStationsSource()
    }

    // Links the database to the repository
    val stationRepository by lazy {
        StationRepository(
            huttValleyStationsSource = huttValleyLocalDataSource
        )
    }
}