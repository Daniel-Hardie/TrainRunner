package com.example.trainrunner.presentation.data.room.sources.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.trainrunner.presentation.Graph
import com.example.trainrunner.presentation.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MetlinkScheduleModel(
    private val repository: Repository = Graph.repository
): ViewModel() {

    init {
        viewModelScope.launch {
            repository
                .deleteAllMetlinkSchedules()
        }
        insertAllMetlinkSchedules()
        insertJVLMetlinkSchedules()
        insertKPLMetlinkSchedules()
        insertMELMetlinkSchedules()
        insertWRLMetlinkSchedules()
    }

    fun insertAllMetlinkSchedules() {
        CoroutineScope(Dispatchers.IO).launch {
            repository
                .insertAllMetlinkSchedules(MetlinkScheduleSourceHVL.schedules)
        }
    }

    fun insertJVLMetlinkSchedules() {
        CoroutineScope(Dispatchers.IO).launch {
            repository
                .insertAllMetlinkSchedules(MetlinkScheduleSourceJVL.schedules)
        }
    }

    fun insertKPLMetlinkSchedules() {
        CoroutineScope(Dispatchers.IO).launch {
            repository
                .insertAllMetlinkSchedules(MetlinkScheduleSourceKPL.schedules)
        }
    }

    fun insertMELMetlinkSchedules() {
        CoroutineScope(Dispatchers.IO).launch {
            repository
                .insertAllMetlinkSchedules(MetlinkScheduleSourceMEL.schedules)
        }
    }

    fun insertWRLMetlinkSchedules() {
        CoroutineScope(Dispatchers.IO).launch {
            repository
                .insertAllMetlinkSchedules(MetlinkScheduleSourceWRL.schedules)
        }
    }
}


@Suppress("UNCHECKED_CAST")
class ScheduleModelFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MetlinkScheduleModel() as T
    }
}