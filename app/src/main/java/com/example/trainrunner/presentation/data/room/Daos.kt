package com.example.trainrunner.presentation.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.trainrunner.presentation.data.room.models.Route
import com.example.trainrunner.presentation.data.room.models.Station
import kotlinx.coroutines.flow.Flow


// For inner join watch https://youtu.be/D7PW4P3FmnU?t=1412
@Dao
interface StationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStation(station: Station)
    @Query("SELECT * FROM stations")
    fun getAllStations(): Flow<List<Station>>

    @Query("SELECT * FROM stations WHERE line =:line")
    fun getAllStationsOnLine(line: String): Flow<List<Station>>

    @Query("SELECT * FROM stations WHERE stationId =:id")
    fun getStationById(id: Int): Flow<Station>
}

@Dao
interface RouteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoute(route: Route)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRoute(route: Route)

    @Delete
    suspend fun deleteRoute(route: Route)

    @Query("""
       SELECT * FROM routes 
    """)
    fun getAllRoutes(): Flow<List<Route>>

    @Query("""
        SELECT * FROM routes 
        WHERE routeId =:routeId
    """)
    fun getRoute(routeId: Int): Flow<Route>
}