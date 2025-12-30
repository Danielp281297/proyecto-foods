package com.daniel.padilla.compactpos.ui.viewModels

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.core.content.contentValuesOf
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class NuevoProductoViewModel @Inject constructor(
    @ApplicationContext private val context: Context
): ProductoFormViewModel() {

    private val _productName = MutableStateFlow("")
    val productName: StateFlow<String> get() = _productName.asStateFlow()

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> get() = _description.asStateFlow()

    private val _preparation = MutableStateFlow("")
    val preparation: StateFlow<String> get() = _preparation.asStateFlow()

    private val _type = MutableStateFlow("")
    val type: StateFlow<String> get() = _type.asStateFlow()

    private val _price = MutableStateFlow("")
    val price: StateFlow<String> get() = _price.asStateFlow()

    private val _showIngredientsDialog = MutableStateFlow<Boolean>(false)
    val showIngredientsDialog: StateFlow<Boolean> get() = _showIngredientsDialog.asStateFlow()

    private val _ingredientsList = MutableStateFlow<MutableList<Pair<String, String>>>(mutableListOf())
    val ingredientsList: StateFlow<MutableList<Pair<String, String>>> get() = _ingredientsList.asStateFlow()

    private val _isAviliableProduct = MutableStateFlow<Boolean>(false)
    val isAviliableProduct: StateFlow<Boolean> get() = _isAviliableProduct.asStateFlow()

    private val _bitmapImage = MutableStateFlow<Bitmap?>(null)
    val bitmapImage: StateFlow<Bitmap?> get() = _bitmapImage.asStateFlow()

    fun setImageBitmap(value: Bitmap){

        _bitmapImage.value = value

    }

    fun getContext(): Context = context

    fun setIsAviliableProduct(value: Boolean){
        _isAviliableProduct.value = value
    }

    fun insertIngredients(ingredient: Pair<String, String>){
        _ingredientsList.value.add(ingredient)
    }

    fun deleteIngredient(index: Int){

        _ingredientsList.value.removeAt(index)

    }

    fun setShowIngredientsDialog(value: Boolean){
        _showIngredientsDialog.value = value
    }

    fun setPrice(value: String){

        if (value.all { it.isDigit() })
            _price.value = value

    }

    fun setType(value: String) { _type.value = value }

    fun setPreparation(value: String){

        if (value.length <= 1000)
            _preparation.value = value

    }

    fun setDescription(value: String){

        if (value.length <= 100)
            _description.value = value
    }

    fun setProductName(value: String){

        if (value.length <= 20)
            _productName.value = value
    }

    fun getImage(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>){

        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        // Crear el Selector
        val chooserIntent = Intent.createChooser(galleryIntent, "Selecciona una opción")

        // La cámara como una opción extra en el mismo menú
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))

        launcher.launch(chooserIntent)

    }

}

