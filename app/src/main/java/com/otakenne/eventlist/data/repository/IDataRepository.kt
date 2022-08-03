package com.otakenne.eventlist.data.repository

import com.otakenne.eventlist.data.datasource.Result
import com.otakenne.eventlist.data.models.EventCategory

interface IDataRepository {
    fun getEvents(fileName: String): Result<EventCategory>
}