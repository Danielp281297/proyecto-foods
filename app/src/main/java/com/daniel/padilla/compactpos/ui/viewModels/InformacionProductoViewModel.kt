package com.daniel.padilla.compactpos.ui.viewModels

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.daniel.padilla.compactpos.database.entities.ProductoObject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class InformacionProductoViewModel @Inject constructor(
    @ApplicationContext private val context: Context
): ViewModel() {

    private val _producto = MutableStateFlow(ProductoObject())
    val producto: StateFlow<ProductoObject> get() = _producto.asStateFlow()

    private val _selectedPage = MutableStateFlow(0)
    val selectedPage: StateFlow<Int> get() = _selectedPage.asStateFlow()

    private val _selectedItem = MutableStateFlow("")
    val selectedItem: StateFlow<String> get() = _selectedItem.asStateFlow()

    private val _bitmapImage = MutableStateFlow<Bitmap?>(null)
    val bitmapImage: StateFlow<Bitmap?> get() = _bitmapImage.asStateFlow()

    fun setSelectedItem(value: String) {
        _selectedItem.value = value
    }

    fun setSelectedPage(value: Int){
        _selectedPage.value = value
    }

    fun setProducto(value: ProductoObject){

        _producto.value = value

    }



}