package com.whatahotel.rates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HotelRatesViewModel : ViewModel() {

    private val repository = HotelRepository()

    private val _hotelRates = MutableLiveData<List<HotelRate>>()
    val hotelRates: LiveData<List<HotelRate>> = _hotelRates

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _lastUpdateTime = MutableLiveData<String>()
    val lastUpdateTime: LiveData<String> = _lastUpdateTime

    init {
        loadHotelRates()
    }

    fun loadHotelRates() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val rates = repository.fetchHotelRates()
                _hotelRates.value = rates
                updateLastUpdateTime()

            } catch (e: Exception) {
                _error.value = "Failed to load hotel rates: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refreshRates() {
        val currentRates = _hotelRates.value ?: run {
            loadHotelRates()
            return
        }

        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val refreshedRates = repository.refreshRates(currentRates)
                _hotelRates.value = refreshedRates
                updateLastUpdateTime()

            } catch (e: Exception) {
                _error.value = "Failed to refresh rates: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun updateLastUpdateTime() {
        val currentTime = java.text.SimpleDateFormat("hh:mm:ss a", java.util.Locale.getDefault())
            .format(java.util.Date())
        _lastUpdateTime.value = "Last updated: $currentTime"
    }
}
