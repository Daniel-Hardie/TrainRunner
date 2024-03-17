package com.example.trainrunner.presentation.data

import androidx.lifecycle.viewmodel.CreationExtras

class StationRepository(
    private val huttValleyStationsSource: HuttValleyStationsSource
) {
    val stations: List<String> = huttValleyStationsSource.stations

//    fun getStations(): List<String>? {
//        return huttValleyStationsSource.stations
//    }

    companion object {
        val INITIAL_STATION_REPOSITORY_KEY = object : CreationExtras.Key<StationRepository> {}
    }
}