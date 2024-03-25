package com.example.trainrunner.presentation.ui.line

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainrunner.presentation.Graph
import com.example.trainrunner.presentation.data.room.models.MetlinkRoute
import com.example.trainrunner.presentation.repository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LineSelectViewModel(
    private val repository: Repository = Graph.repository
) : ViewModel() {
    var state by mutableStateOf(LineSelectState())
        private set

    init {
        viewModelScope.launch {
            repository
                .getAllMetlinkRoutes
                .collectLatest {
                    if (it != null) {
                        state = state.copy(
                            lineList = it
                        )
                    }
                }
        }

    }
}

data class LineSelectState(
    val lineList: List<MetlinkRoute> = emptyList()
)