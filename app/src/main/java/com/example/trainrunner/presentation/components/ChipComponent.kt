package com.example.trainrunner.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.horologist.compose.material.Chip
import com.google.android.horologist.images.base.paintable.DrawableResPaintable

@Composable
fun ChipComponent(
    title: String,
    secondaryLabel: String,
    @DrawableRes chipIcon: Int ?= null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if(chipIcon == null){
        Chip(
            modifier = modifier,
            label = title,
            secondaryLabel = secondaryLabel,
            onClick = onClick
        )
    }
    else{
        Chip(
            modifier = modifier,
            icon = DrawableResPaintable(chipIcon),
            label = title,
            secondaryLabel = secondaryLabel,
            onClick = onClick
        )
    }
}