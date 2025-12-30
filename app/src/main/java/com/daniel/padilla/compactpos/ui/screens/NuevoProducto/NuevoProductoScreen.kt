package com.daniel.padilla.compactpos.ui.screens.NuevoProducto

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.service.autofill.Dataset
import android.util.Log
import android.view.ViewGroup
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.daniel.padilla.compactpos.R
import com.daniel.padilla.compactpos.ui.components.button.CustomToggleButton
import com.daniel.padilla.compactpos.ui.components.image.ProductoImage
import com.daniel.padilla.compactpos.ui.layout.MainLayout
import com.daniel.padilla.compactpos.ui.theme.background
import com.daniel.padilla.compactpos.ui.theme.cursorColor
import com.daniel.padilla.compactpos.ui.theme.textFieldColor
import com.daniel.padilla.compactpos.ui.viewModels.NuevoProductoViewModel
import com.deoslv.bancaribe.utils.CurrencyAmountInputVisualTransformation
import com.nextgo.disconnect.ui.layout.ButtonLayout
import kotlinx.coroutines.launch
import java.io.File
import java.util.concurrent.Executor

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun NuevoProductoScreen(
    navController: NavController,
    viewModel: NuevoProductoViewModel = hiltViewModel()
){

    val uri = remember{ mutableStateOf<Uri?>(null)}

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->

        if (result.resultCode == Activity.RESULT_OK){
            val uri = result.data?.data!!
            val source = ImageDecoder.createSource(viewModel.getContext().contentResolver, uri)
            val bitmap = ImageDecoder.decodeBitmap(source)

            viewModel.setImageBitmap(bitmap)

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



            }
            Column(modifier = Modifier
                .weight(2F)
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
                    ) { viewModel.setType(it) }

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

@Composable
fun IngredientsDialog(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onAcceptValue: (String) -> Unit
){

    val ingredients = remember { mutableStateOf("") }

    DialogLayout(
        show,
        onDismissRequest = onDismissRequest,
    ) {

        Box(modifier = Modifier
            .background(background)
            .padding(15.dp)) {
            Column(
                modifier = Modifier
            ) {
                SubtitleGlobal("NUEVO INGREDIENTE")
                HorizontalDivider()

                TextFieldGlobal(
                    singleLine = true,
                    value = ingredients.value,
                    onValueChange = { ingredients.value = it },
                    label = {
                        SubtitleGlobal("INGREDIENTE")
                    },
                    textStyle = TextStyle(fontSize = 16.sp)
                )

                ButtonLayout(
                    title = "GUARDAR PRODUCTO"
                ) { onAcceptValue(ingredients.value) }
            }

        }
    }

}

@Composable
fun DialogLayout(
    show: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
){

    if (show) {
        Dialog(
            onDismissRequest = onDismissRequest
        ) { content() }
    }

}

@Composable
fun IngredientsField(
    modifier: Modifier = Modifier,
    dataset: List<Pair<String, String>>,
    deleteOnClick: (Int) -> Unit
){

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .then(modifier))
    {

        items(dataset.size){ index ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(dataset[index].first)
                Text(dataset[index].second)
                IconButton(
                    onClick = {
                        deleteOnClick(index)
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(15.dp),
                        painter = painterResource(R.drawable.borrar_icon),
                        contentDescription = null)
                }

            }

        }

    }

}

@Composable
fun SubtitleGlobal(
    subtitle: String,
){
    Text(subtitle,
        color = Color.Gray,
        fontWeight = FontWeight.SemiBold)
}

@Composable
fun IconGlobal(
    modifier: Modifier = Modifier,
    iconResource: Int,
){

    Icon(
        painter = painterResource(iconResource),
        modifier = Modifier
            .size(20.dp)
            .then(modifier),
        contentDescription = null
    )

}

@Composable
fun TextFieldGlobal(
    modifier: Modifier = Modifier,
    title: String? = null,
    value: String,
    singleLine: Boolean = false,
    isPassword: Boolean = false,
    isLastField: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Password,
    textStyle: TextStyle = LocalTextStyle.current,
    visualTransformation: VisualTransformation? = null,
    supportText: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit
){

    Column() {

        if (title != null)
            SubtitleGlobal(title)

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier),
            value = value,
            trailingIcon = trailingIcon,
            onValueChange = onValueChange,
            singleLine = singleLine,
            label = label,
            textStyle = textStyle,
            supportingText = supportText,
            visualTransformation = visualTransformation ?: VisualTransformation.None,
            keyboardOptions = KeyboardOptions().copy(
                keyboardType = keyboardType,
                imeAction = if (isLastField) ImeAction.Done else ImeAction.Next
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,

                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,

                focusedTextColor = textFieldColor,
                unfocusedTextColor = textFieldColor,

                cursorColor = cursorColor
            )
        )
    }

}

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