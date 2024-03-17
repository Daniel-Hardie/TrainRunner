package com.example.trainrunner.presentation.repository

import com.example.trainrunner.presentation.data.room.RouteDao
import com.example.trainrunner.presentation.data.room.StationDao
import com.example.trainrunner.presentation.data.room.models.Route


// Can use DI library to set up, look into
class Repository(
    private val stationDao: StationDao,
    private val routeDao: RouteDao
) {
    // Route table
    fun getRouteById(id: Int) = routeDao
        .getRoute(id)
    val getAllRoutes = routeDao.getAllRoutes()

    suspend fun insertRoute(route: Route){
        routeDao.insertRoute(route)
    }
    suspend fun updateRoute(route: Route){
        routeDao.updateRoute(route)
    }
    suspend fun deleteRoute(route: Route){
        routeDao.deleteRoute(route)
    }


    // Station table
    val getAllStations = stationDao.getAllStations()
    fun getStationById(id: Int) = stationDao
        .getStationById(id)
    fun getAllStationsOnLine(line: String) = stationDao
        .getAllStationsOnLine(line)
}