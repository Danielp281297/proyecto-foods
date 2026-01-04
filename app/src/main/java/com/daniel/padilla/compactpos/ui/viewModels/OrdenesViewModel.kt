package com.daniel.padilla.compactpos.ui.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class OrdenesViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _selectedPage = MutableStateFlow(0)
    val selectedPage: StateFlow<Int> get() = _selectedPage.asStateFlow()

    private val _selectedItem = MutableStateFlow("ACTUAL")
    val selectedItem: StateFlow<String> get() = _selectedItem.asStateFlow()

    fun setSelectedItem(value: String) {
        _selectedItem.value = value
    }

    fun setSelectedPage(value: Int){

        _selectedPage.value = value

    }

}