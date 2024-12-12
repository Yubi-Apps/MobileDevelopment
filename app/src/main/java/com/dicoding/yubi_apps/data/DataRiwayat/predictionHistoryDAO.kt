package com.dicoding.yubi_apps.data.DataRiwayat

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PredictionHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrediction(prediction: PredictionHistory): Long

    @Query("SELECT * FROM prediction_history")
    suspend fun getAllPredictions(): List<PredictionHistory>

    @Delete
    suspend fun deletePrediction(prediction: PredictionHistory)
}