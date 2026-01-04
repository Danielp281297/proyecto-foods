package com.daniel.padilla.compactpos.ui.components.Button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nextgo.disconnect.ui.layout.ButtonLayout

@Composable
fun FilledButton(
    modifier: Modifier = Modifier,
    title: String,
    icon: Int,
    enable: Boolean = true,
    onClick: () -> Unit
){

    ButtonLayout(
        modifier = modifier,
        title = title,
        icon = icon,
        enabled = enable,
    ) { onClick() }

}