package com.daniel.padilla.compactpos.ui.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject

@HiltViewModel
class NuevoProductoViewModel @Inject constructor(
    @ApplicationContext val context: Context
): ViewModel() {

    init {
        Log.d(null, "NUEVO PRODUCTO FORMULARIO OBTENIDO")
    }

}