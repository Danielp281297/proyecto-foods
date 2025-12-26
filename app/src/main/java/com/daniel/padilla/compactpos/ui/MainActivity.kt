package com.daniel.padilla.compactpos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.daniel.padilla.compactpos.ui.screens.NuevoProducto
import com.daniel.padilla.compactpos.ui.theme.CompactPOSTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompactPOSTheme {

                Navigation()

            }
        }
    }
}

enum class Route{
    NUEVO_PRODUCTO
}

@Composable
fun Navigation(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.NUEVO_PRODUCTO.name){

        composable(route = Route.NUEVO_PRODUCTO.name){

            NuevoProducto(navController)

        }


    }

}