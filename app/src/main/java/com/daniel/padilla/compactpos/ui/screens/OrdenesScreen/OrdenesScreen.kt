package com.daniel.padilla.compactpos.ui.screens.OrdenesScreen

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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.daniel.padilla.compactpos.ui.components.Button.CustomToggleButton
import com.daniel.padilla.compactpos.ui.components.Text.SubtitleGlobal
import com.daniel.padilla.compactpos.ui.layout.MainLayout
import com.daniel.padilla.compactpos.ui.viewModels.OrdenesViewModel
import kotlinx.coroutines.launch

@Composable
fun OrdenesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: OrdenesViewModel = hiltViewModel()
){

    val scope = rememberCoroutineScope()
    val selectedPage = viewModel.selectedPage.collectAsState()
    val selectedItem = viewModel.selectedItem.collectAsState()
    val pagerState = rememberPagerState(initialPage = selectedPage.value, pageCount = { 2 })


    MainLayout(
            navController
        ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                //.background(Color.Red)
                .then(modifier),
        ) {

            Text("Bienvenido: ")
            Text("NOMBRE DE LA EMPRESA")
            Text("RIF")

            CustomToggleButton(
                title = "ORDENES",
                selectedItem = selectedItem.value,
                dataset = listOf("ACTUAL", "GUARDADOS")
            ) { value, index ->

                scope.launch {
                    viewModel.apply {
                        setSelectedPage(index)
                        setSelectedItem(value)
                    }
                    pagerState.animateScrollToPage(selectedPage.value)

                }

            }

            HorizontalPager(
                modifier = Modifier.fillMaxWidth().weight(1F),
                state = pagerState,
                userScrollEnabled = false
            ) {

                when(selectedPage.value){

                    0 -> {
                        ActualOrder(
                            onClick = {}
                        ) {

                        }
                    }

                    1 -> {
                        SavedOrdersList(
                            modifier = Modifier.weight(1F)
                        ) {

                        }
                    }

                }

            }
        }

    }
}

@Composable
fun ActualOrder(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit
) {

    Column(
        modifier = Modifier.then(modifier)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3F)
        ) {

            items(5) { index ->

                ActualOrderItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(vertical = 5.dp)
                )

            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
        ) {

            SubtitleGlobal("SUBTOTAL")
            SubtitleGlobal("IVA")
            HorizontalDivider()
            SubtitleGlobal("TOTAL")

        }

    }

}

@Composable
fun ActualOrderItem(
    modifier: Modifier = Modifier
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .then(modifier)
    ) {

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1F)
                .background(Color.Blue),
            contentAlignment = Alignment.Center
        ) {


        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(2F)
                .background(Color.Red)
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("NOMBRE")
                    Text("X")
                }
                Text("CATEGORIA")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text("PRECIO")
                Text("CANTIDAD")

            }

        }

    }

}

@Composable
fun SavedOrdersList(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            then(modifier),
    ) {

        items(20){

            SavedOrdersItem(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .background(Color.Red)
                    .height(100.dp)
                    .clickable{

                        onClick()

                    },
            )

        }

    }
}

@Composable
fun SavedOrdersItem(
    modifier: Modifier = Modifier
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
    ) {

        Text("FECHA")
        Text("HORA")
        Text("PRODUCTOS")
        Text("SUBTOTAL")

    }

}