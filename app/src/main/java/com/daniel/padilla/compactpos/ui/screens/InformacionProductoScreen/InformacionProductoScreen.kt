package com.daniel.padilla.compactpos.ui.screens.InformacionProductoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.daniel.padilla.compactpos.R
import com.daniel.padilla.compactpos.ui.Route
import com.daniel.padilla.compactpos.ui.components.IconGlobal
import com.daniel.padilla.compactpos.ui.components.Text.SubtitleGlobal
import com.daniel.padilla.compactpos.ui.components.Button.CustomToggleButton
import com.daniel.padilla.compactpos.ui.components.Button.FilledButton
import com.daniel.padilla.compactpos.ui.components.image.ProductoImage
import com.daniel.padilla.compactpos.ui.layout.MainLayout
import com.daniel.padilla.compactpos.ui.viewModels.InformacionProductoViewModel
import kotlinx.coroutines.launch

@Composable
fun InformacionProductoScreen(
    navController: NavController,
    viewModel: InformacionProductoViewModel = hiltViewModel()
){

    val scope = rememberCoroutineScope()

    val producto = viewModel.producto.collectAsState()
    val selectedItem = viewModel.selectedItem.collectAsState()
    val selectedPage = viewModel.selectedPage.collectAsState()
    val pagerState = rememberPagerState(initialPage = selectedPage.value, pageCount = { 2 })

    MainLayout(
        navController,
        floatingActionButton = {

            FloatingActionButton(
                onClick = { navController.navigate(Route.NUEVO_PRODUCTO.name) },
                elevation = FloatingActionButtonDefaults.elevation()
            ) {

                IconGlobal(
                    iconResource = R.drawable.edit_icon
                )

            }

        }
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            ProductoImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .background(Color.Red),
                bitmap = producto.value.imageBitmap,
                imageInt = R.drawable.placeholder_template,
                isNewProductOrToModifier = false
            ) {}

            Column(
                modifier = Modifier.fillMaxWidth().weight(1F)
            ) {

                SubtitleGlobal("NOMBRE")
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                        SubtitleGlobal("CATEGORIA")
                        SubtitleGlobal("$")
                }

                CustomToggleButton(
                    selectedItem = selectedItem.value,
                    dataset = listOf("Descripcion", "Preparacion")
                ) { value, index ->

                    viewModel.setSelectedItem(value)
                    viewModel.setSelectedPage(index)

                    scope.launch {

                        pagerState.animateScrollToPage(selectedPage.value)
                        return@launch

                    }


                }

                HorizontalPager(
                    modifier = Modifier.fillMaxWidth().weight(1F),
                    state = pagerState,
                    userScrollEnabled = false
                ) {

                    when(selectedPage.value){

                        0 -> { DescriptionScreen() }
                        1 -> { PreparationScreen() }

                    }

                }

                Column(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)) {

                    FilledButton(
                        title = "BORRAR PRODUCTO",
                        icon = R.drawable.borrar_icon
                    ) { }

                    FilledButton(
                        title = "ORDENAR",
                        icon = R.drawable.agregar_icon
                    ) { }

                }

            }


        }

    }

}

@Composable
fun DescriptionScreen(
    modifier: Modifier = Modifier,
){

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .then(modifier)
    ) {

        Text("Descripcion")

    }

}

@Composable
fun PreparationScreen(
    modifier: Modifier = Modifier,
){

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .then(modifier)
    ) {

        Text("Preparacion")

    }

}