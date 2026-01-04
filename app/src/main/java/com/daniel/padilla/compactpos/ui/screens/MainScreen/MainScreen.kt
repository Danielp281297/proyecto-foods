package com.daniel.padilla.compactpos.ui.screens.MainScreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavController
){

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    // Se maneja cuando el usuario pulsa el boton atras cuando el menu lateral esta abierto
    BackHandler(drawerState.isOpen) {
        if(drawerState.isOpen)
        {
            scope.launch { drawerState.close() }
            drawerState.isClosed
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // El contenido del menu lateral
            ModalDrawerSheet(modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth())
            {



            }
        }
    )
    {



    }

}