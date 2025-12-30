package com.daniel.padilla.compactpos.ui.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class ProductoFormViewModel() : ViewModel() {

    private val _selectedItem = MutableStateFlow("")
    val selectedItem: StateFlow<String> get() = _selectedItem.asStateFlow()

    fun setSelectedItem(value: String) { _selectedItem.value = value }

}