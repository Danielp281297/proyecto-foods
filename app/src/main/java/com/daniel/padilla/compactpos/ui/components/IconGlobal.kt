package com.daniel.padilla.compactpos.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconGlobal(
    modifier: Modifier = Modifier,
    iconResource: Int,
){

    Icon(
        painter = painterResource(iconResource),
        modifier = Modifier
            .size(20.dp)
            .then(modifier),
        contentDescription = null
    )

}