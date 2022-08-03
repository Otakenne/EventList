package com.otakenne.eventlist.utility

import com.otakenne.eventlist.data.models.Event
import java.util.concurrent.atomic.AtomicInteger

class EventFactory {
    private val startPoint = 0
    private val counter = AtomicInteger(startPoint)

    fun createEvent(): Event {
        val id = counter.incrementAndGet()

        return Event(
            id = id,
            name = "Some Name $id",
            venueName = "Some Venue $id",
            city = "Some City $id",
            price = id * 2000,
            distanceFromVenue = 0.0f,
            date = "Jul 21, 2022"
        )
    }
}