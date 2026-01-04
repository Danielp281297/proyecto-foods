package com.daniel.padilla.compactpos.utils

import android.content.ContentValues
import android.provider.MediaStore
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import java.io.File
import java.util.concurrent.Executor

fun capturePicture(
    cameraController: LifecycleCameraController,
    executor: Executor,
    directorio: File
) {
    val image = File.createTempFile("img_", ".jpg", directorio)


    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "picture")
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
        put(MediaStore.MediaColumns.IS_PENDING, 1)
    }

    val metadata = ImageCapture.Metadata().apply{
        isReversedHorizontal = true
    }

    val outputDirectory
            = ImageCapture.OutputFileOptions.Builder(image).setMetadata(metadata).build()

    cameraController.takePicture(
        outputDirectory,
        executor,
        object : ImageCapture.OnImageSavedCallback
        {
            override fun onImageSaved(p0: ImageCapture.OutputFileResults) {
                println(p0.savedUri)
                Log.d("PhotoMessage", "Foto tomada con exito")
            }

            override fun onError(p0: ImageCaptureException) {
                Log.d("ErrorMessage", p0.message!!)
            }

        }
    )
}