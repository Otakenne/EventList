package com.otakenne.eventlist.ui.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.otakenne.eventlist.data.models.Event
import com.otakenne.eventlist.ui.theme.EventListTheme
import com.otakenne.eventlist.ui.viewmodels.DataViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventList(viewModel: DataViewModel) {
    val events by viewModel.event.observeAsState()
    val errorMessage by viewModel.errorMessage.observeAsState()

    events?.let {
        LazyColumn {
            val grouped = events!!.groupBy { it.name }
            grouped.forEach { (initial, items) ->
                stickyHeader {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Text(
                            initial,
                            modifier = Modifier.padding(20.dp)
                        )
                    }
                }
                items(items) { event ->
                    EventRow(event = event)
                }
            }
        }
    }

    errorMessage?.let {
        ErrorLayout(error = errorMessage!!)
    }
}

@Composable
fun EventRow(event: Event) {
    Column(Modifier.padding(20.dp)) {
        Text("Name: ${event.name}")
        Text("Venue: ${event.venueName}")
        Text("City: ${event.city}")
        Text("Price: NGN${event.price}")
        Text("Date: ${event.date}")
    }
}

@Composable
fun ErrorLayout(error: String) {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
        Text(
            text = error,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EventListTheme {
        EventRow(
            Event(
            9,
            "Test Event",
            "Maddison Square Garden",
            "New York",
            20000,
            0.0f,
            "Jun 17, 2022"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorLayoutPreview() {
    EventListTheme {
        ErrorLayout("An error has occurred with the call, please try again")
    }
}