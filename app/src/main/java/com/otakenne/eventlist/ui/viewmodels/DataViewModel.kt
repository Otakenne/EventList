package com.otakenne.eventlist.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.otakenne.eventlist.data.datasource.Result
import com.otakenne.eventlist.data.models.Event
import com.otakenne.eventlist.data.repository.IDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.NonCancellable.children
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val dataRepository: IDataRepository
): ViewModel() {
    private val _events = MutableLiveData<List<Event>>()
    val event: LiveData<List<Event>> = _events

    private val _errorMessage = MutableLiveData<String>(null)
    val errorMessage: LiveData<String> = _errorMessage

    private var _cityQuery: String? = null
    val cityQuery: String?
        get() = _cityQuery

    private var _priceFilter: Int? = null
    val priceFilter: Int?
        get() = _priceFilter

    init {
        getEvents()
    }

    fun getEvents(fileName: String = "Events.json", cityQuery: String? = null, priceFilter: Int? = null) {
        when (val eventCategory = dataRepository.getEvents(fileName)) {
            is Result.Success -> {
                val events = eventCategory.content.children
                    .flatMap { it.children }
                    .flatMap { it.events }

                _errorMessage.value = null
                _events.value = if (cityQuery != null && priceFilter != null) {
                    _cityQuery = cityQuery.trim().lowercase()
                    _priceFilter = priceFilter
                    events.filter { it.city.lowercase().contains(cityQuery.trim().lowercase()) }
                        .filter { it.price <= priceFilter }
                } else if (cityQuery != null && priceFilter == null) {
                    _cityQuery = cityQuery.trim().lowercase()
                    events.filter { it.city.lowercase().contains(cityQuery.trim().lowercase()) }
                } else if (cityQuery == null && priceFilter != null) {
                    _priceFilter = priceFilter
                    events.filter { it.price <= priceFilter }
                } else {
                    events
                }
            }
            is Result.Error -> {
                _events.value = listOf()
                _errorMessage.value = eventCategory.throwable.localizedMessage
            }
        }
    }
}