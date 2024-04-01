package com.example.trainrunner.presentation.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.trainrunner.presentation.data.room.converters.DateConverter
import com.example.trainrunner.presentation.data.room.models.MetlinkRoute
import com.example.trainrunner.presentation.data.room.models.MetlinkSchedule
import com.example.trainrunner.presentation.data.room.models.Route
import com.example.trainrunner.presentation.data.room.models.RouteNotification
import com.example.trainrunner.presentation.data.room.models.Station

@TypeConverters(value = [DateConverter::class])
@Database(
    entities = [Station::class, Route::class, RouteNotification::class, MetlinkRoute::class, MetlinkSchedule::class],
    version = 1,
    exportSchema = false   //not sure why it is false
)

// Manages creation and connection of database
abstract class TrainRunnerDatabase: RoomDatabase() {
    abstract fun stationDao(): StationDao
    abstract fun routeDao(): RouteDao
    abstract fun routeNotificationDao(): RouteNotificationDao
    abstract fun metlinkRouteDao(): MetlinkRouteDao
    abstract fun metlinkScheduleDao(): MetlinkScheduleDao

    // Helps create instance of this class without instantiating it
    companion object {
        // @Volatile manages in a thread-safe manner
        @Volatile
        var INSTANCE: TrainRunnerDatabase? = null

        // Synchronized ensures only thread ones for this
        fun getDatabase(context: Context): TrainRunnerDatabase{
            // If database is null, create and return
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    TrainRunnerDatabase::class.java,
                    "train_runner_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}