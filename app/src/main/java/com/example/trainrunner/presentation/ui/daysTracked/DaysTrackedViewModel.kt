package com.example.trainrunner.presentation.ui.daysTracked

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


data class Day(
    val orderId: Int = -1,
    val text: String = "",
    val shortText: String = "",
    val isActive: Boolean = false
)