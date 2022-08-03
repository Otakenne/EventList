package com.otakenne.eventlist.data.repository

import com.otakenne.eventlist.data.datasource.IDataSource
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val dataSource: IDataSource
): IDataRepository {
    override fun getEvents(fileName: String) = dataSource.loadJSON(fileName)
}