package com.otakenne.eventlist.data.datasource

import com.otakenne.eventlist.data.models.EventCategory
import com.otakenne.eventlist.utility.EventFactory

class MockDataSource: IDataSource {
    override fun loadJSON(fileName: String): Result<EventCategory> {
        val eventFactory = EventFactory()
        val mockCities = listOf(
            eventFactory.createEvent(),
            eventFactory.createEvent()
        )
        val eventCategory = EventCategory(
            id = 0,
            name = "An event Category",
            events = mockCities,
            children = listOf()
        )
        val anotherEventCategory = EventCategory(
            id = 1,
            name = "A parent event Category",
            events = listOf(),
            children = listOf(eventCategory)
        )
        val parentEventCategory = EventCategory(
            id = 1,
            name = "A parent event Category",
            events = listOf(),
            children = listOf(anotherEventCategory)
        )
        return Result.Success(parentEventCategory)
    }
}