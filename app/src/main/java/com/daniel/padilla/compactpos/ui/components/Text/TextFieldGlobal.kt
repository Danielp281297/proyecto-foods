package com.daniel.padilla.compactpos.ui.components.Text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.daniel.padilla.compactpos.ui.theme.cursorColor
import com.daniel.padilla.compactpos.ui.theme.textFieldColor

@Composable
fun TextFieldGlobal(
    modifier: Modifier = Modifier,
    title: String? = null,
    value: String,
    singleLine: Boolean = false,
    isPassword: Boolean = false,
    isLastField: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Password,
    textStyle: TextStyle = LocalTextStyle.current,
    visualTransformation: VisualTransformation? = null,
    supportText: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit
){

    Column() {

        if (title != null)
            SubtitleGlobal(title)

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier),
            value = value,
            trailingIcon = trailingIcon,
            onValueChange = onValueChange,
            singleLine = singleLine,
            label = label,
            textStyle = textStyle,
            supportingText = supportText,
            visualTransformation = visualTransformation ?: VisualTransformation.None,
            keyboardOptions = KeyboardOptions().copy(
                keyboardType = keyboardType,
                imeAction = if (isLastField) ImeAction.Done else ImeAction.Next
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,

                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,

                focusedTextColor = textFieldColor,
                unfocusedTextColor = textFieldColor,

                cursorColor = cursorColor
            )
        )
    }

}