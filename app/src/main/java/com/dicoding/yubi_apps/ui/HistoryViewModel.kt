package com.dicoding.yubi_apps.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.dicoding.yubi_apps.data.DataRiwayat.PredictionHistory
import com.dicoding.yubi_apps.data.repository.HistoryRepository
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: HistoryRepository) : ViewModel() {

    private val _allHistoryData = MutableLiveData<List<PredictionHistory>>()
    val allHistoryData get() = _allHistoryData

    private val _historyData = MutableLiveData<Long>()
    val historyData get() = _historyData

    init {
        getAllHistory()
    }

    fun getAllHistory() {
        viewModelScope.launch {
            repository.getAllHistory().observeForever() { data ->
                _allHistoryData.postValue(data)
            }
        }
    }

    fun insert(history: PredictionHistory) {
        viewModelScope.launch {
            repository.insert(history)
            getAllHistory()
        }
    }
}