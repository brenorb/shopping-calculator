package com.example.unitcalculator

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CalculationDao {
    @Insert
    suspend fun insert(calculation: Calculation)

    @Query("SELECT * FROM calculations ORDER BY timestamp DESC")
    fun getAll(): Flow<List<Calculation>>
}