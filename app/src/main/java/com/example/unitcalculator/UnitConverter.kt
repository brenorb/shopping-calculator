package com.example.unitcalculator

object UnitConverter {
    fun calculatePerUnitPrice(price: Double, quantity: Double, unit: String): Double {
        val conversionFactor = when (unit) {
            "g" -> 1.0
            "kg" -> 1000.0
            "oz" -> 28.35
            "lb" -> 453.592
            "ml" -> 1.0
            "l" -> 1000.0
            "fl oz" -> 29.5735
            "gal" -> 3785.41
            else -> 1.0
        }
        return price / (quantity * conversionFactor)
    }
}