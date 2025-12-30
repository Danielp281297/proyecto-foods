package com.daniel.padilla.compactpos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.daniel.padilla.compactpos.ui.Navigation
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



