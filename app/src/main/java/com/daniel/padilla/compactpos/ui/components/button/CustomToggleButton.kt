package com.daniel.padilla.compactpos.ui.components.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.daniel.padilla.compactpos.ui.screens.NuevoProducto.SubtitleGlobal

@Composable
fun CustomToggleButton(
    modifier: Modifier = Modifier,
    title: String? = null,
    selectedItem: String,
    dataset: List<String>,
    cornerPercent: Int = 50,
    onClick: (String) -> Unit
){

    Column {

        if (title != null)
            SubtitleGlobal(title)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(percent = cornerPercent))
                .height(50.dp)
                .padding(5.dp)
                .then(modifier)
        ) {

            dataset.forEach {

                val backgroundColor =
                    animateColorAsState(
                        targetValue = if (selectedItem == it) Color.LightGray else Color.Transparent,
                        animationSpec = tween(durationMillis = 150)
                    )

                val fontColor = animateColorAsState(
                    targetValue = Color.Black,
                    animationSpec = tween(durationMillis = 150)
                )

                Surface(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1F)
                        .clip(RoundedCornerShape(percent = cornerPercent))
                        .clickable {
                            onClick(it)
                        },
                    color = backgroundColor.value
                ) {
                    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                        Text(it, color = fontColor.value)
                    }
                }

            }

        }
    }
}
