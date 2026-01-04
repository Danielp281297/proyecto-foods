package com.daniel.padilla.compactpos.ui.screens.ListaProductoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.daniel.padilla.compactpos.ui.components.IconGlobal
import com.daniel.padilla.compactpos.ui.components.Text.TextFieldGlobal
import com.daniel.padilla.compactpos.ui.layout.MainLayout
import com.daniel.padilla.compactpos.ui.theme.CompactPOSTheme
import com.daniel.padilla.compactpos.ui.viewModels.ListaProductoViewModel
import com.daniel.padilla.compactpos.R
import com.daniel.padilla.compactpos.database.entities.ProductoObject
import com.daniel.padilla.compactpos.ui.Route

@Composable
@Preview(showBackground = true)
fun ScreenViewer(){

    val navController = rememberNavController()

    CompactPOSTheme{

        ListaProductosScreen(
            navController
        )

    }

}

@Composable
fun ListaProductosScreen(
    navController: NavController,
    viewModel: ListaProductoViewModel = hiltViewModel()
){

    val nameFilter = viewModel.nameFilter.collectAsState()

    MainLayout(
        navController = navController,
        floatingActionButton ={

            FloatingActionButton(
                onClick = { navController.navigate(Route.NUEVO_PRODUCTO.name) },
                elevation = FloatingActionButtonDefaults.elevation()
            ) {

                IconGlobal(
                    iconResource = R.drawable.agregar_icon
                )

            }

        }
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            // Un filtro de busqueda por entrada de texto
            TextFieldGlobal(
                value = nameFilter.value,
                title = "BUSQUEDA",
                trailingIcon = {
                    IconGlobal(iconResource = R.drawable.search_icon)
                }
            ) {

                viewModel.setNameFilter(it)

            }

            // Un filtro por categorias
            HorizontalFilter(
                dataset = listOf("")
            ) {



            }

            ListOrGridOptions {

            }

            ProductsDeplay(
                modifier = Modifier.weight(1F),
                dataset = listOf()
            ) { }

        }

    }

}

data class ListOrGridOptionData(
    val key: String,
    val value: Int,
    val icon: Int
)

@Composable
fun ProductsDeplay(
    modifier: Modifier = Modifier,
    dataset: List<ProductoObject>,
    onClick: (ProductoObject) -> Unit
){

    Column(modifier = Modifier.fillMaxWidth().then(modifier)){

        //Iconos del orden

        // Elementos
        LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)) {

            items(dataset.size){ index ->

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clickable{

                        onClick(dataset[index])

                    }) {

                    Box(modifier = Modifier.fillMaxHeight().weight(1F).background(Color.Blue),
                        contentAlignment = Alignment.Center){


                    }

                    Column(
                        modifier = Modifier.fillMaxHeight().weight(2F),
                        verticalArrangement = Arrangement.spacedBy(5.dp)) {

                        Text("Nombre", fontWeight = FontWeight.SemiBold, overflow = TextOverflow.Ellipsis)
                        Text("Tipo", overflow = TextOverflow.Ellipsis)
                        Text("Marca", overflow = TextOverflow.Ellipsis)
                        Text("Precio", fontWeight = FontWeight.Bold, overflow = TextOverflow.Ellipsis)


                    }

                }

            }

        }

    }

}

@Composable
fun ListOrGridOptions(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
){

    val options = listOf(
        ListOrGridOptionData(
            key = "Lista",
            value = 1,
            icon = R.drawable.list_icon
        ),
        ListOrGridOptionData(
            key = "Grilla",
            value = 2,
            icon = R.drawable.grid_icon
        )
    )

    // Mostrar los elementos en forma de grilla oe lista
    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .height(75.dp)
        .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.End)){

        items(options.size){index ->

            IconButton(modifier = Modifier,
                onClick = { onClick(options[index].key) }) {

                Icon(
                    painter = painterResource(options[index].icon),
                    contentDescription = null
                )

            }

        }


    }

}

@Composable
fun HorizontalFilter(
    modifier: Modifier = Modifier,
    dataset: List<String>,
    onClick: (String) -> Unit
){

    LazyRow(modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .then(modifier),
        horizontalArrangement = Arrangement.spacedBy(10.dp)) {

        items(dataset.size){ index ->

            Box(modifier = Modifier.fillMaxHeight().clickable{

                onClick(dataset[index])

            },
                contentAlignment = Alignment.Center){

                Text(dataset[index])

            }

        }

    }

}