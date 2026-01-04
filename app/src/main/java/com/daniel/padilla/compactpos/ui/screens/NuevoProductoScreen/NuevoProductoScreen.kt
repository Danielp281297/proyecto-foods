package com.daniel.padilla.compactpos.ui.screens.NuevoProductoScreen

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.daniel.padilla.compactpos.R
import com.daniel.padilla.compactpos.ui.components.Dialogs.IngredientsDialog
import com.daniel.padilla.compactpos.ui.components.IconGlobal
import com.daniel.padilla.compactpos.ui.components.Text.SubtitleGlobal
import com.daniel.padilla.compactpos.ui.components.Text.TextFieldGlobal
import com.daniel.padilla.compactpos.ui.components.Button.CustomToggleButton
import com.daniel.padilla.compactpos.ui.components.image.ProductoImage
import com.daniel.padilla.compactpos.ui.layout.MainLayout
import com.daniel.padilla.compactpos.ui.screens.NuevoProductoScreen.components.IngredientsField
import com.daniel.padilla.compactpos.ui.viewModels.NuevoProductoViewModel
import com.deoslv.bancaribe.utils.CurrencyAmountInputVisualTransformation
import com.nextgo.disconnect.ui.layout.ButtonLayout

@Composable
fun NuevoProductoScreen(
    navController: NavController,
    viewModel: NuevoProductoViewModel = hiltViewModel()
){

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->

        if (result.resultCode == Activity.RESULT_OK){

            val data = result.data
            val imageUri = data?.data

            if (imageUri != null) { // GALERIA

                val source = ImageDecoder.createSource(viewModel.getContext().contentResolver, imageUri)
                val bitmap = ImageDecoder.decodeBitmap(source)

                viewModel.setImageBitmap(bitmap)

            } else {  // CAMARA

                val thumbnail = data?.extras?.get("data") as? Bitmap
                if (thumbnail != null) {
                    viewModel.setImageBitmap(thumbnail)
                }
            }
        }
    }

    val scrollState = rememberScrollState()

    val typeOptions = listOf("Comida","Bebida","Producto")

    val startToDelete = remember{ mutableStateOf(false) }
    val indexToDelete = remember{mutableStateOf(-1)}

    val productName = viewModel.productName.collectAsState()
    val description = viewModel.description.collectAsState()
    val preparation = viewModel.preparation.collectAsState()
    val type = viewModel.type.collectAsState()
    val price = viewModel.price.collectAsState()
    val ingredientsList = viewModel.ingredientsList.collectAsState()
    val showIngredietsDialog = viewModel.showIngredientsDialog.collectAsState()
    val isAviliableProduct = viewModel.isAviliableProduct.collectAsState()
    val bitmapImage = viewModel.bitmapImage.collectAsState()

    MainLayout(navController) {

        Column(modifier = Modifier.fillMaxSize()) {

            ProductoImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .background(Color.Red),
                bitmap = bitmapImage.value,
                imageInt = R.drawable.placeholder_template,
                isNewProductOrToModifier = true
            ) {

                viewModel.getImage(launcher)

            }
            Column(modifier = Modifier
                .weight(1F)
                .padding(15.dp)) {

                Column(modifier = Modifier
                    .weight(1F)
                    .verticalScroll(scrollState)) {

                    /*CustomToggleButton(
                        dataset = listOf("Option 1","Option 2","Option 3","Option 4"),
                        selectedItem = selectedItem.value
                    ){ viewModel.setSelectedItem(it) }*/

                    TextFieldGlobal(
                        value = productName.value,
                        onValueChange = { viewModel.setProductName(it) },
                        singleLine = true,
                        label = {
                            SubtitleGlobal("NOMBRE DE PRODUCTO")
                        },
                        trailingIcon = {
                            IconGlobal(iconResource = R.drawable.edit_icon)
                        },
                        supportText = {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                Text("${productName.value.length}/20",)
                            }
                        },
                        textStyle = TextStyle(fontSize = 24.sp)
                    )

                    CustomToggleButton(
                        dataset = typeOptions,
                        selectedItem = type.value
                    ) { value, index ->  viewModel.setType(value) }

                    TextFieldGlobal(
                        modifier = Modifier.height(150.dp),
                        value = description.value,
                        onValueChange = { viewModel.setDescription(it) },
                        label = {
                            SubtitleGlobal("DESCRIPCION")
                        },
                        trailingIcon = {
                            IconGlobal(iconResource = R.drawable.edit_icon)
                        },
                        supportText = {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                Text("${description.value.length}/100",)
                            }
                        },
                        textStyle = TextStyle(fontSize = 16.sp)
                    )

                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {
                        SubtitleGlobal("TIPO")
                        IconButton(
                            onClick = {

                            }
                        ) {
                            IconGlobal(iconResource = R.drawable.agregar_icon)
                        }
                    }

                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {
                        SubtitleGlobal("INGREDIENTES")
                        IconButton(
                            onClick = {
                                viewModel.setShowIngredientsDialog(true)
                            }
                        ) {
                            IconGlobal(
                                iconResource = R.drawable.agregar_icon)
                        }
                    }

                    IngredientsField(
                        modifier = Modifier.height(150.dp),
                        dataset = ingredientsList.value.toList(),
                        deleteOnClick = { index ->
                            indexToDelete.value = index
                            startToDelete.value = true
                        }
                    )

                    TextFieldGlobal(
                        modifier = Modifier.height(150.dp),
                        value = preparation.value,
                        onValueChange = { viewModel.setPreparation(it) },
                        label = {
                            SubtitleGlobal("PREPARACION")
                        },
                        trailingIcon = {
                            IconGlobal(iconResource = R.drawable.edit_icon)
                        },
                        supportText = {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                Text("${preparation.value.length}/1000",)
                            }
                        },
                        textStyle = TextStyle(fontSize = 16.sp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        SubtitleGlobal("PRECIO: ")

                        TextFieldGlobal(

                            value = price.value,
                            onValueChange = { viewModel.setPrice(it) },
                            singleLine = true,
                            trailingIcon = {
                                IconGlobal(iconResource = R.drawable.dolar_icon)
                            },
                            keyboardType = KeyboardType.NumberPassword,
                            textStyle = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Right),
                            visualTransformation = CurrencyAmountInputVisualTransformation()
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SubtitleGlobal("PRODUCTO DISPONIBLE?")
                        Switch(
                            checked = isAviliableProduct.value,
                            onCheckedChange = {
                                viewModel.setIsAviliableProduct(!isAviliableProduct.value)
                            },
                            thumbContent = {

                                Text(if (isAviliableProduct.value) "SI" else "NO")

                            }
                        )
                    }

                }

            }

            ButtonLayout(
                title = "BORRAR"
            ) {

            }

            ButtonLayout(
                title = "GUARDAR PRODUCTO"
            ) {

            }
        }

    }

    if (startToDelete.value)
        LaunchedEffect(Unit) {

            viewModel.deleteIngredient(indexToDelete.value)
            startToDelete.value = false

        }

    IngredientsDialog(
        showIngredietsDialog.value,
        onDismissRequest = {viewModel.setShowIngredientsDialog(false)}
    ) {

        viewModel.insertIngredients(Pair(it, "0 mg"))
        viewModel.setShowIngredientsDialog(false)

    }

}