package com.daniel.padilla.compactpos.ui.components.Dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.padilla.compactpos.ui.components.Text.SubtitleGlobal
import com.daniel.padilla.compactpos.ui.components.Text.TextFieldGlobal
import com.daniel.padilla.compactpos.ui.layout.DialogLayout
import com.daniel.padilla.compactpos.ui.theme.background
import com.nextgo.disconnect.ui.layout.ButtonLayout

@Composable
fun IngredientsDialog(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onAcceptValue: (String) -> Unit
){

    val ingredients = remember { mutableStateOf("") }

    DialogLayout(
        show,
        onDismissRequest = onDismissRequest,
    ) {

        Box(modifier = Modifier
            .background(background)
            .padding(15.dp)) {
            Column(
                modifier = Modifier
            ) {
                SubtitleGlobal("NUEVO INGREDIENTE")
                HorizontalDivider()

                TextFieldGlobal(
                    singleLine = true,
                    value = ingredients.value,
                    onValueChange = { ingredients.value = it },
                    label = {
                        SubtitleGlobal("INGREDIENTE")
                    },
                    textStyle = TextStyle(fontSize = 16.sp)
                )

                ButtonLayout(
                    title = "GUARDAR PRODUCTO"
                ) { onAcceptValue(ingredients.value) }
            }

        }
    }

}