package com.example.trainrunner.presentation.data.room.sources.route

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.trainrunner.presentation.Graph
import com.example.trainrunner.presentation.repository.Repository
import kotlinx.coroutines.launch

class MetlinkRouteModel(
    private val repository: Repository = Graph.repository
) : ViewModel() {

    init {
        viewModelScope.launch {
            repository
                .deleteAllMetlinkRoutes()

            MetlinkRouteSource.routes.forEach {
                route -> repository
                .insertMetlinkRoute(route)
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MetlinkRouteModelFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MetlinkRouteModel() as T
    }
}