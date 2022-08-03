package com.otakenne.eventlist.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.otakenne.eventlist.ui.viewmodels.DataViewModel

@Composable
fun CityQueryComposable(viewModel: DataViewModel) {
    var query by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = query,
        placeholder = { Text("Query City") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 5.dp),
        label = { Text(text = "City Query") },
        onValueChange = { newText ->
            query = newText
            viewModel.getEvents(
                cityQuery = query,
                priceFilter = viewModel.priceFilter
            )
        }
    )
}

@Composable
fun PriceQueryComposable(viewModel: DataViewModel) {
    var priceFilter by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = priceFilter,
        placeholder = { Text("Filter Price") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 5.dp, end = 20.dp, bottom = 20.dp),
        label = { Text(text = "Price Filter") },
        onValueChange = { newText ->
            priceFilter = newText
            val priceFilterInt = priceFilter.toIntOrNull()
            viewModel.getEvents(
                cityQuery = viewModel.cityQuery,
                priceFilter = priceFilterInt
            )
        }
    )
}