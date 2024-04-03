package com.example.trainrunner.presentation.repository

import com.example.trainrunner.presentation.data.room.MetlinkRouteDao
import com.example.trainrunner.presentation.data.room.MetlinkScheduleDao
import com.example.trainrunner.presentation.data.room.RouteDao
import com.example.trainrunner.presentation.data.room.RouteNotificationDao
import com.example.trainrunner.presentation.data.room.StationDao
import com.example.trainrunner.presentation.data.room.SystemNotificationDao
import com.example.trainrunner.presentation.data.room.models.MetlinkRoute
import com.example.trainrunner.presentation.data.room.models.MetlinkSchedule
import com.example.trainrunner.presentation.data.room.models.Route
import com.example.trainrunner.presentation.data.room.models.RouteNotification
import com.example.trainrunner.presentation.data.room.models.Station
import com.example.trainrunner.presentation.data.room.models.SystemNotification


// Can use DI library to set up, look into
class Repository(
    private val stationDao: StationDao,
    private val routeDao: RouteDao,
    private val routeNotificationDao: RouteNotificationDao,
    private val metlinkRouteDao: MetlinkRouteDao,
    private val metlinkScheduleDao: MetlinkScheduleDao,
    private val systemNotificationDao: SystemNotificationDao
) {
    // Route table
    fun getRouteById(id: Int) = routeDao.getRouteById(id)

    fun getRouteByUniqueId(id: Int) = routeDao.getRouteByUniqueId(id)

    val getAllRoutes = routeDao.getAllRoutes()

    val getNumberRoutes = routeDao.getNumberRoutes()

    fun getNumberRouteDaysTracked(id: Int) = routeDao.getNumberRouteDaysTracked(id)

    // Returning id of newly inserted route to be used as FK in RouteNotification table
    suspend fun insertRoute(route: Route) {
        return routeDao.insertRoute(route)
    }
    suspend fun updateRoute(route: Route){
        routeDao.updateRoute(route)
    }
    suspend fun deleteRoute(routeId: Int){
        routeDao.deleteRoute(routeId)
    }


    // Station table
    val getAllStations = stationDao.getAllStations()
    fun getStationById(id: Int) = stationDao.getStationById(id)
    fun getAllStationsOnLineByLineShortName(lineShortName: String) = stationDao.getAllStationsOnLineByLineShortName(lineShortName)
    fun getStopIdByParentNameAndDirection(stationCode: String, toWellington: Boolean) = stationDao.getStopIdByParentNameAndDirection(stationCode, toWellington)
    fun getStationByCode(code: String) = stationDao.getStationByCode(code)
    suspend fun insertStation(station: Station){
        stationDao.insertStation(station)
    }
    suspend fun deleteAllStations(){
        stationDao.deleteAllStations()
    }


    // RouteNotification table
    val getAllRouteNotifications = routeNotificationDao.getAllRouteNotifications()

    fun getRouteNotificationsByUniqueId(id: Int) = routeNotificationDao.getRouteNotificationsByUniqueId(id)

    suspend fun insertRouteNotification(routeNotification: RouteNotification){
        routeNotificationDao.insertRouteNotification(routeNotification)
    }

    suspend fun insertRouteNotificationList(routeNotificationList: List<RouteNotification>){
        routeNotificationDao.insertRouteNotificationList(routeNotificationList)
    }
    suspend fun updateRouteNotification(routeNotification: RouteNotification){
        routeNotificationDao.updateRouteNotification(routeNotification)
    }
    suspend fun deleteRouteNotification(routeId: Int){
        routeNotificationDao.deleteRouteNotification(routeId)
    }


    // MetlinkRoute table
    val getAllMetlinkRoutes = metlinkRouteDao.getAllMetlinkRoutes()

    suspend fun insertMetlinkRoute(metlinkRoute: MetlinkRoute){
        metlinkRouteDao.insertMetlinkRoute(metlinkRoute)
    }

    suspend fun deleteAllMetlinkRoutes(){
        metlinkRouteDao.deleteAllMetlinkRoutes()
    }


    // MetlinkSchedule table
    fun getAvailableTimesForStop(selectedStationOneCode: String, selectedStationTwoCode: String) =
        metlinkScheduleDao.getAvailableTimesForStop(selectedStationOneCode, selectedStationTwoCode)

    suspend fun insertAllMetlinkSchedules(metlinkSchedules: List<MetlinkSchedule>){
        metlinkScheduleDao.insertAllMetlinkSchedules(metlinkSchedules)
    }

    suspend fun deleteAllMetlinkSchedules(){
        metlinkScheduleDao.deleteAllMetlinkSchedules()
    }


    // SystemNotification table
    val getNextSystemNotification = systemNotificationDao.getNextSystemNotification()
    suspend fun insertSystemNotificationList(systemNotification: List<SystemNotification>){
        systemNotificationDao.insertSystemNotificationList(systemNotification)
    }
}