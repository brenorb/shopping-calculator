package com.example.unitcalculator

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Data model representing a single price calculation.
 *
 * Stored in Room database to maintain calculation history.
 */
@Entity(tableName = "calculations")
data class Calculation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val itemName: String,
    val price: Double,
    val quantity: Double,
    val unit: String,
    val perUnitPrice: Double,  // Calculated price per base unit
    val timestamp: Date = Date()
)