package com.example.trainrunner.presentation.ui.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainrunner.presentation.Graph
import com.example.trainrunner.presentation.repository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: Repository = Graph.repository
) : ViewModel() {

    var state by mutableStateOf(SettingsInformation())
        private set

    init {
        getRouteCount()
    }

    fun getRouteCount(){
        viewModelScope.launch {
            repository
                .getNumberRoutes
                .collectLatest {
                    state = state.copy(
                        numberOfRoutes = it
                    )
                }
        }
    }

}


data class SettingsInformation(
    val numberOfRoutes: Int = 0
)