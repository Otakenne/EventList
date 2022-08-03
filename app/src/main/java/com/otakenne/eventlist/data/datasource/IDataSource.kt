package com.otakenne.eventlist.data.datasource

import com.otakenne.eventlist.data.models.EventCategory

interface IDataSource {
    fun loadJSON(fileName: String): Result<EventCategory>
}