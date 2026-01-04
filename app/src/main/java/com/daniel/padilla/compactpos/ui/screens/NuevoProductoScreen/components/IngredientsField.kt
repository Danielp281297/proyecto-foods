package com.daniel.padilla.compactpos.ui.screens.NuevoProductoScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.daniel.padilla.compactpos.R

@Composable
fun IngredientsField(
    modifier: Modifier = Modifier,
    dataset: List<Pair<String, String>>,
    deleteOnClick: (Int) -> Unit
){

    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .then(modifier))
    {

        items(dataset.size){ index ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(dataset[index].first)
                Text(dataset[index].second)
                IconButton(
                    onClick = {
                        deleteOnClick(index)
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(15.dp),
                        painter = painterResource(R.drawable.borrar_icon),
                        contentDescription = null)
                }

            }

        }

    }

}