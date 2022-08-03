package com.otakenne.eventlist.data.models

data class Event(
    val id: Int,
    val name: String,
    val venueName: String,
    val city: String,
    val price: Int,
    val distanceFromVenue: Float,
    val date: String,
)
