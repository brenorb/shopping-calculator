package com.example.unitcalculator

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "calculations")
data class Calculation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val itemName: String,
    val price: Double,
    val quantity: Double,
    val unit: String,
    val perUnitPrice: Double,
    val timestamp: Date = Date()
)