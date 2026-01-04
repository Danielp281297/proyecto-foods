package com.daniel.padilla.compactpos.ui.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
fun DialogLayout(
    show: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
){

    if (show) {
        Dialog(
            onDismissRequest = onDismissRequest
        ) { content() }
    }

}