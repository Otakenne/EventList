package com.otakenne.eventlist.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.otakenne.eventlist.data.datasource.IDataSource
import com.otakenne.eventlist.data.repository.IDataRepository
import com.otakenne.eventlist.data.datasource.MockDataSource
import com.otakenne.eventlist.data.repository.MockRepository
import com.otakenne.eventlist.utility.getOrAwaitValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DataViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var dataSource: IDataSource
    private lateinit var repository: IDataRepository
    private lateinit var viewModel: DataViewModel

    @Before
    fun setup() {
        dataSource = MockDataSource()
        repository = MockRepository(dataSource)
        viewModel = DataViewModel(repository)
    }

    @Test
    fun getNoEventsWithWildCityQueryAndPriceFilterTest() {
        viewModel.getEvents(fileName = "", cityQuery = "Cam", priceFilter = 1000)
        val filteredEvents = viewModel.event.getOrAwaitValue()
        assertTrue(filteredEvents.isEmpty())
    }

    @Test
    fun getEventWithCityQueryTest() {
        viewModel.getEvents(fileName = "", cityQuery = "2", priceFilter = null)
        val filteredEvents = viewModel.event.getOrAwaitValue()
        val eventOfInterest = filteredEvents[0]
        assertTrue(eventOfInterest.name == "Some Name 2")
    }

    @Test
    fun getEventWithPriceFilterTest() {
        viewModel.getEvents(fileName = "", cityQuery = null, priceFilter = 3000)
        val filteredEvents = viewModel.event.getOrAwaitValue()
        val eventOfInterest = filteredEvents[0]
        assertTrue(eventOfInterest.name == "Some Name 1")
    }

    @Test
    fun getAllEventsWithoutCityQueryAndPriceFilterTest() {
        viewModel.getEvents(fileName = "", cityQuery = null, priceFilter = null)
        val filteredEvents = viewModel.event.getOrAwaitValue()
        assertTrue(filteredEvents.size == 2)
    }

    @Test
    fun getAllEventsWithCityQueryAndPriceFilterTest() {
        viewModel.getEvents(fileName = "", cityQuery = "City", priceFilter = 5000)
        val filteredEvents = viewModel.event.getOrAwaitValue()
        assertTrue(filteredEvents.size == 2)
    }
}