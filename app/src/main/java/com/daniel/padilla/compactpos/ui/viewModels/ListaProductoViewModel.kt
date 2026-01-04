package com.daniel.padilla.compactpos.ui.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.daniel.padilla.compactpos.database.entities.ProductoObject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ListaProductoViewModel @Inject constructor(
    @ApplicationContext private val context: Context
): ViewModel()
{

    private val _nameFilter = MutableStateFlow("")
    val nameFilter: StateFlow<String> get() = _nameFilter.asStateFlow()

    private val _listaProductos = MutableStateFlow<List<ProductoObject>>(emptyList())
    val listaProducto: StateFlow<List<ProductoObject>> get() = _listaProductos.asStateFlow()

    fun setNameFilter(value: String){

        _nameFilter.value = value

    }

    private fun setListaProductos(value: List<ProductoObject>){

        _listaProductos.value = value

    }

    // Un metodo para filtrar los productos por categoria o tipo
    fun CategoryFilter(value: String){



    }

}