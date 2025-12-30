package com.nextgo.disconnect.ui.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun ButtonLayout(
    modifier: Modifier = Modifier,
    title: String,
    icon: Int? = null,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    border: BorderStroke? = null,
    onClick: () -> Unit
){

    Button(
        modifier = Modifier.fillMaxWidth().then(modifier),
        onClick = onClick,
        enabled = enabled,
        colors = colors,
        border = border
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            if (icon != null)
                Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(icon),contentDescription = null)
            Text(title, color = colors.contentColor)
        }

    }

}
