package com.daniel.padilla.compactpos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.daniel.padilla.compactpos.ui.viewModels.NuevoProductoViewModel

@Composable
fun NuevoProducto(
    navController: NavController,
    viewModel: NuevoProductoViewModel = hiltViewModel()
){

    Column(modifier = Modifier.fillMaxSize().background(Color.Red)) {



    }

}