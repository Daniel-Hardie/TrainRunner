package com.example.trainrunner.presentation

import android.app.Application

class BaseApplication : Application() {
//    // Loads the database
//    private val huttValleyLocalDataSource by lazy {
//        HuttValleyStationsSource()
//    }
//
//    // Links the database to the repository
//    val stationRepository by lazy {
//        StationRepository(
//            huttValleyStationsSource = huttValleyLocalDataSource
//        )
//    }

    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}