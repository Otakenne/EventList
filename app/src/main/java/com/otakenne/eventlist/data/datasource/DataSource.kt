package com.otakenne.eventlist.data.datasource

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.otakenne.eventlist.data.models.EventCategory
import java.io.IOException
import javax.inject.Inject

class DataSource @Inject constructor(
    private val context: Context
): IDataSource {
    override fun loadJSON(fileName: String): Result<EventCategory> {
        val jsonString = readJSONFromFile(context, fileName)
        val gson = Gson()
        val eventCategoryType = object : TypeToken<EventCategory>() {}.type
        return try {
            val eventCategory: EventCategory = gson.fromJson(jsonString, eventCategoryType)
            Result.Success(eventCategory)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    private fun readJSONFromFile(context: Context, fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (exception: IOException) {
            null
        }
    }
}