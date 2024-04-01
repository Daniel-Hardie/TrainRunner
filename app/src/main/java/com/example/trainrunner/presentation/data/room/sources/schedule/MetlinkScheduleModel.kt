package com.example.trainrunner.presentation.data.room.sources.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.trainrunner.presentation.Graph
import com.example.trainrunner.presentation.repository.Repository
import kotlinx.coroutines.launch

class MetlinkScheduleModel(
    private val repository: Repository = Graph.repository
): ViewModel() {

    init {
        viewModelScope.launch {
            repository
                .deleteAllMetlinkSchedules()

            repository
                .insertAllMetlinkSchedules(MetlinkScheduleSource.schedules)
        }
    }
}


@Suppress("UNCHECKED_CAST")
class ScheduleModelFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MetlinkScheduleModel() as T
    }
}