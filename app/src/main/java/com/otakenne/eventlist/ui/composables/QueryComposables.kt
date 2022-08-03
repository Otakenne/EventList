package com.otakenne.eventlist.ui.composables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import com.otakenne.eventlist.ui.viewmodels.DataViewModel

@Composable
fun CityQueryComposable(viewModel: DataViewModel) {
    var query by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = query,
        placeholder = { Text("Query City") },
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