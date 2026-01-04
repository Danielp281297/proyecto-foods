package com.daniel.padilla.compactpos.ui.screens.CameraScreen

import android.content.Context
import android.content.Intent
import android.os.Environment
import android.provider.MediaStore
import android.view.ViewGroup
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.daniel.padilla.compactpos.utils.capturePicture

@Composable
fun CameraScreen(context: Context)
{
    val lensFacing = CameraSelector.LENS_FACING_BACK

    val cameraController = remember{
        LifecycleCameraController(context)
    }

    val lifecycle = LocalLifecycleOwner.current
    cameraController.bindToLifecycle(lifecycle)

    val directorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).absoluteFile

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
        // Aquí puedes manejar el resultado de la captura de la imagen
    }

    LaunchedEffect(true) {
        launcher.launch(Intent(MediaStore.ACTION_PICK_IMAGES))
    }
    Box(modifier = Modifier.fillMaxSize().padding(50.dp))
    {
        AndroidView(factory = { context ->
            val previewView = PreviewView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }

            previewView.controller = cameraController

            previewView

        }, modifier = Modifier.fillMaxSize())

        Row(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        )
        {
            Button(onClick = {
                val executor = ContextCompat.getMainExecutor(context)
                capturePicture(cameraController, executor, directorio)
            }
                , modifier = Modifier.size(70.dp)){
                Text("")
            }
        }
    }

}