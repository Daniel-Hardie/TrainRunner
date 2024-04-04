package com.example.trainrunner.presentation.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.trainrunner.presentation.data.room.models.MetlinkRoute
import com.example.trainrunner.presentation.data.room.models.MetlinkSchedule
import com.example.trainrunner.presentation.data.room.models.Route
import com.example.trainrunner.presentation.data.room.models.RouteNotification
import com.example.trainrunner.presentation.data.room.models.Station
import com.example.trainrunner.presentation.data.room.models.SystemNotification
import kotlinx.coroutines.flow.Flow


// For inner join watch https://youtu.be/D7PW4P3FmnU?t=1412
@Dao
interface StationDao {
    @Query("""
        SELECT * FROM station
    """)
    fun getAllStations(): Flow<List<Station>>

    @Query("""
        SELECT DISTINCT
         CASE WHEN s.metlinkParentStation != ""
         THEN s.metlinkParentStation
         ELSE s.metlinkStopId
        END AS stationName
        FROM station s 
        INNER JOIN metlinkRoute mr ON s.metlinkRouteId = mr.metlinkRouteId
        WHERE mr.metlinkRouteShortName =:lineShortName
        ORDER BY s.metlinkStopId
    """)
    fun getAllStationsOnLineByLineShortName(lineShortName: String): Flow<List<String>>

    @Query("""
        SELECT
        *
        FROM station s
        WHERE CASE WHEN s.metlinkParentStation != ""
            THEN 
                s.metlinkParentStation = :stationCode
            ELSE 
                s.metlinkStopId = :stationCode         
                AND CASE WHEN :toWellington == true
                    THEN (toWellington == true)
                    ELSE (fromWellington == false)
                END
                AND CASE WHEN :toWellington == false
                    THEN (fromWellington == true)
                    ELSE (toWellington == false)
                END
        END

    """)
    fun getStopIdByParentNameAndDirection(stationCode: String, toWellington: Boolean): Flow<List<Station>>

    @Query("""
        SELECT * FROM station WHERE stationId =:id
    """)
    fun getStationById(id: Int): Flow<Station>

    @Query("""
        SELECT * FROM station WHERE metlinkStopCode =:code
    """)
    fun getStationByCode(code: String): Flow<Station>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStation(station: Station)

    @Query("""
        DELETE FROM station
    """)
    suspend fun deleteAllStations()
}

@Dao
interface RouteDao {
    @Query("""
       SELECT * FROM route 
    """)
    fun getAllRoutes(): Flow<List<Route>>

    @Query("""
        SELECT * FROM route
        WHERE routeId = :routeId
    """)
    fun getRouteById(routeId: Int): Flow<Route>

    @Query("""
        SELECT * FROM route
        WHERE uniqueValueForRouteNotification = :uniqueRouteId
    """)
    fun getRouteByUniqueId(uniqueRouteId: Int): Flow<Route>

    @Query("""
        SELECT COUNT(*) from route
    """)
    fun getNumberRoutes(): Flow<Int>

    @Query("""
       SELECT COUNT(*) FROM route AS r
       INNER JOIN routeNotification AS rn ON r.routeId = rn.routeUniqueId
       WHERE r.routeId =:routeId
    """
    )
    fun getNumberRouteDaysTracked(routeId: Int): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoute(route: Route)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRoute(route: Route)

    @Query("""
        DELETE FROM route
        WHERE routeId = :routeId
    """)
    suspend fun deleteRoute(routeId: Int)
}

@Dao
interface RouteNotificationDao {
    @Query("""
       SELECT * FROM routeNotification
    """)
    fun getAllRouteNotifications(): Flow<List<RouteNotification>>

    @Query("""
       SELECT * FROM routeNotification
       WHERE routeUniqueId = :uniqueId
    """)
    fun getRouteNotificationsByUniqueId(uniqueId: Int): Flow<List<RouteNotification>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRouteNotification(routeNotification: RouteNotification)

    @Insert
    suspend fun insertRouteNotificationList(routeNotificationList: List<RouteNotification>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRouteNotification(routeNotification: RouteNotification)

    @Query("""
        DELETE FROM routeNotification
        WHERE routeUniqueId =:routeId
    """)
    suspend fun deleteRouteNotification(routeId: Int)
}

@Dao
interface MetlinkRouteDao {
    @Query("""
       SELECT * FROM metlinkRoute
    """)
    fun getAllMetlinkRoutes(): Flow<List<MetlinkRoute>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMetlinkRoute(metlinkRoute: MetlinkRoute)

    @Query("""
        DELETE FROM metlinkRoute
    """)
    suspend fun deleteAllMetlinkRoutes()
}

@Dao
interface MetlinkScheduleDao {
    @Query("""
        SELECT ms.* FROM metlinkSchedule ms 
        INNER JOIN metlinkSchedule ms2 ON ms.tripId = ms2.tripId
        WHERE ms.parentStationCode = :selectedStationOneCode
        AND ms2.parentStationCode = :selectedStationTwoCode
    """)
    fun getAvailableTimesForStop(selectedStationOneCode: String, selectedStationTwoCode: String): Flow<List<MetlinkSchedule>>
    @Insert
    suspend fun insertAllMetlinkSchedules(scheduleList: List<MetlinkSchedule>)

    @Query("""
        DELETE FROM metlinkSchedule
    """)
    suspend fun deleteAllMetlinkSchedules()
}

@Dao
interface SystemNotificationDao{
    @Query("""
        SELECT * 
        FROM SystemNotification
        ORDER BY nextAlertDateTime ASC 
        LIMIT 1
    """)
    fun getNextSystemNotification(): Flow<SystemNotification>

    @Insert
    suspend fun insertSystemNotificationList(systemNotification: List<SystemNotification>)
}