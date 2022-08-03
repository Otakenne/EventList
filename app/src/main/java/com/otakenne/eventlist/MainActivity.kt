package com.otakenne.eventlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.otakenne.eventlist.ui.composables.CityQueryComposable
import com.otakenne.eventlist.ui.composables.EventList
import com.otakenne.eventlist.ui.composables.PriceQueryComposable
import com.otakenne.eventlist.ui.theme.EventListTheme
import com.otakenne.eventlist.ui.viewmodels.DataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: DataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        CityQueryComposable(viewModel = viewModel)
                        PriceQueryComposable(viewModel = viewModel)
                        EventList(viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EventListTheme {
    }
}