package com.daniel.padilla.compactpos.ui.components.Text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun SubtitleGlobal(
    subtitle: String,
){
    Text(subtitle,
        color = Color.Gray,
        fontWeight = FontWeight.SemiBold)
}