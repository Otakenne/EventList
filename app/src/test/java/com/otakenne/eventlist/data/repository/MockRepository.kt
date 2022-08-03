package com.otakenne.eventlist.data.repository

import com.otakenne.eventlist.data.datasource.IDataSource
import com.otakenne.eventlist.data.datasource.Result
import com.otakenne.eventlist.data.models.EventCategory
import com.otakenne.eventlist.data.repository.IDataRepository

class MockRepository(
    private val dataSource: IDataSource
): IDataRepository {
    override fun getEvents(fileName: String): Result<EventCategory> {
        return dataSource.loadJSON(fileName)
    }
}