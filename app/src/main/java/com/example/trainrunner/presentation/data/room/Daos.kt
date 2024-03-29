package com.example.trainrunner.presentation.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.trainrunner.presentation.data.room.models.MetlinkRoute
import com.example.trainrunner.presentation.data.room.models.Route
import com.example.trainrunner.presentation.data.room.models.RouteNotification
import com.example.trainrunner.presentation.data.room.models.Station
import kotlinx.coroutines.flow.Flow


// For inner join watch https://youtu.be/D7PW4P3FmnU?t=1412
@Dao
interface StationDao {
    @Query("""
        SELECT * FROM station
    """)
    fun getAllStations(): Flow<List<Station>>

    @Query("""
        SELECT * FROM station s 
        INNER JOIN metlinkRoute mr ON s.metlinkRouteId = mr.metlinkRouteId
        WHERE mr.metlinkRouteShortName =:lineShortName
    """)
    fun getAllStationsOnLine(lineShortName: String): Flow<List<Station>>

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
        WHERE routeId =:routeId
    """)
    fun getRoute(routeId: Int): Flow<Route>

    @Query("""
       SELECT COUNT(*) FROM route AS r
       INNER JOIN routeNotification AS rn ON r.routeId = rn.routeId
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
        WHERE routeId =:routeId
    """)
    suspend fun deleteRoute(routeId: Int)
}

@Dao
interface RouteNotificationDao {
    @Query("""
       SELECT * FROM routeNotification
    """)
    fun getAllRouteNotifications(): Flow<List<RouteNotification>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRouteNotification(routeNotification: RouteNotification)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRouteNotification(routeNotification: RouteNotification)

    @Query("""
        DELETE FROM routeNotification
        WHERE routeId =:routeId
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