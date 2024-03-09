package com.example.trainrunner.presentation.theme

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.MaterialTheme

// See: https://developer.android.com/jetpack/compose/designsystems/custom
@Composable
fun TrainRunnerTheme(
    colors: Colors = initialThemeValues.colors,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = colors,
        typography = WearTypography,
        content = content
    )
}
