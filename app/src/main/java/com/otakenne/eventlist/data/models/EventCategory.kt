package com.otakenne.eventlist.data.models

data class EventCategory(
    val id: Int,
    val name: String,
    val events: List<Event>,
    val children: List<EventCategory>
)
