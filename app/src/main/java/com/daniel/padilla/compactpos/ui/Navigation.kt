package com.daniel.padilla.compactpos.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.daniel.padilla.compactpos.ui.screens.InformacionProductoScreen.InformacionProductoScreen
import com.daniel.padilla.compactpos.ui.screens.ListaProductoScreen.ListaProductosScreen
import com.daniel.padilla.compactpos.ui.screens.MainScreen.MainScreen
import com.daniel.padilla.compactpos.ui.screens.NuevoProductoScreen.NuevoProductoScreen
import com.daniel.padilla.compactpos.ui.screens.OrdenesScreen.OrdenesScreen

enum class Route{
    NUEVO_PRODUCTO,
    LISTA_PRODUCTO,
    INFORMACION_PRODUCTO,
    MAIN,
    ORDENES,
    CONSULTA_VENTAS,
    INDICADORES,
    SETTING
}

@Composable
fun Navigation(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.ORDENES.name){

        composable(route = Route.NUEVO_PRODUCTO.name){

            NuevoProductoScreen(navController)

        }

        composable(route = Route.LISTA_PRODUCTO.name) {

            ListaProductosScreen(navController)

        }

        composable(route = Route.INFORMACION_PRODUCTO.name) {

            InformacionProductoScreen(navController)

        }

        composable(route = Route.ORDENES.name) {

            OrdenesScreen(navController = navController)

        }

    }

}