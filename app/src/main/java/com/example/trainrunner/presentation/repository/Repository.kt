package com.example.trainrunner.presentation.repository

import com.example.trainrunner.presentation.data.room.RouteDao
import com.example.trainrunner.presentation.data.room.RouteNotificationDao
import com.example.trainrunner.presentation.data.room.StationDao
import com.example.trainrunner.presentation.data.room.models.Route
import com.example.trainrunner.presentation.data.room.models.RouteNotification
import com.example.trainrunner.presentation.data.room.models.Station


// Can use DI library to set up, look into
class Repository(
    private val stationDao: StationDao,
    private val routeDao: RouteDao,
    private val routeNotificationDao: RouteNotificationDao
) {
    // Route table
    fun getRoute(id: Int) = routeDao.getRoute(id)

    val getAllRoutes = routeDao.getAllRoutes()

    fun getNumberRouteDaysTracked(id: Int) = routeDao.getNumberRouteDaysTracked(id)

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
    fun getStationById(id: Int) = stationDao.getStationById(id)
    fun getAllStationsOnLine(lineId: Int) = stationDao.getAllStationsOnLine(lineId)
    fun getStationByCode(code: String) = stationDao.getStationByCode(code)
    suspend fun insertStation(station: Station){
        stationDao.insertStation(station)
    }


    // Route Notification Table
    val getAllRouteNotifications = routeNotificationDao.getAllRouteNotifications()
    suspend fun insertRouteNotification(routeNotification: RouteNotification){
        routeNotificationDao.insertRouteNotification(routeNotification)
    }
    suspend fun updateRouteNotification(routeNotification: RouteNotification){
        routeNotificationDao.updateRouteNotification(routeNotification)
    }
    suspend fun deleteRouteNotification(routeNotification: RouteNotification){
        routeNotificationDao.deleteRouteNotification(routeNotification)
    }
}