package com.daniel.padilla.compactpos.ui.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun MainLayout(
    navController: NavController,
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable () -> Unit
){

    Scaffold(
        floatingActionButton = floatingActionButton
    ) {

        Box(modifier = Modifier.padding(it)){
            content()
        }

    }

}