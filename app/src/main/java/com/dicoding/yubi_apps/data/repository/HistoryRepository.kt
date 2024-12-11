package com.dicoding.yubi_apps.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.yubi_apps.data.DataRiwayat.PredictionHistory
import com.dicoding.yubi_apps.data.DataRiwayat.PredictionHistoryDao

class HistoryRepository(
    private val historyDao: PredictionHistoryDao
) {
    suspend fun insert(history: PredictionHistory): Long {
        return historyDao.insertPrediction(history)
    }

    suspend fun delete(history: PredictionHistory) {
        return historyDao.deletePrediction(history)
    }

    fun getAllHistory() =
        liveData {
            val data = historyDao.getAllPredictions()
            emit(data)
        }
}