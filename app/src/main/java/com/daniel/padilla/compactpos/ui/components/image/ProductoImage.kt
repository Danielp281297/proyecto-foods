package com.daniel.padilla.compactpos.ui.components.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.daniel.padilla.compactpos.R
import com.daniel.padilla.compactpos.ui.screens.NuevoProducto.IconGlobal

@Composable
fun ProductoImage(
    modifier: Modifier = Modifier,
    imageInt: Int,
    bitmap: Bitmap?,
    isNewProductOrToModifier: Boolean,
    onClick: () -> Unit
){

    Box(modifier = modifier,
        contentAlignment = Alignment.Center) {

        if (bitmap != null) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }else
            Image(
                painter = painterResource(imageInt),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )


        if (isNewProductOrToModifier) {
            IconButton(
                modifier = Modifier.padding(10.dp).clip(RoundedCornerShape(percent = 100)).background(Color.Gray.copy(alpha = 0.5F))
                    .align(Alignment.BottomEnd),
                onClick = { onClick() })
            {

                IconGlobal(
                    iconResource = R.drawable.edit_icon
                )

            }
        }

    }

}