package com.example.trainrunner.presentation

import android.content.Context
import com.example.trainrunner.presentation.data.room.TrainRunnerDatabase
import com.example.trainrunner.presentation.repository.Repository

object Graph {
    lateinit var db: TrainRunnerDatabase
        private set

    val repository by lazy {
        Repository(
            stationDao = db.stationDao(),
            routeDao = db.routeDao(),
            routeNotificationDao = db.routeNotificationDao(),
            metlinkRouteDao = db.metlinkRouteDao(),
            metlinkScheduleDao = db.metlinkScheduleDao()
        )
    }

    fun provide(context: Context){
        db = TrainRunnerDatabase.getDatabase(context)
    }
}