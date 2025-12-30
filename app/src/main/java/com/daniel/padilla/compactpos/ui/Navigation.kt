package com.daniel.padilla.compactpos.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.daniel.padilla.compactpos.ui.screens.NuevoProducto.NuevoProductoScreen

enum class Route{
    NUEVO_PRODUCTO
}

@Composable
fun Navigation(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.NUEVO_PRODUCTO.name){

        composable(route = Route.NUEVO_PRODUCTO.name){

            NuevoProductoScreen(navController)

        }


    }

}