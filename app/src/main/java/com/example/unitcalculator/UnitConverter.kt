package com.example.unitcalculator

/**
 * Utility for normalizing units and calculating per-unit prices.
 *
 * All units are converted to a base unit for comparison:
 * - Weight: grams (g)
 * - Volume: milliliters (ml)
 * - Count: items
 */
object UnitConverter {
    
    /**
     * Calculates the price per standardized unit.
     *
     * @param price The total price of the item
     * @param quantity The quantity in the given unit
     * @param unit The unit type (g, kg, oz, lb, ml, l, fl oz, gal, count)
     * @return Price per base unit (gram for weight, ml for volume, item for count)
     *
     * Example: calculatePerUnitPrice(5.99, 1.0, "kg") returns 0.00599 (price per gram)
     */
    fun calculatePerUnitPrice(price: Double, quantity: Double, unit: String): Double {
        // Conversion factors to base units (grams for weight, ml for volume)
        val conversionFactor = when (unit) {
            "g" -> 1.0
            "kg" -> 1000.0
            "oz" -> 28.35
            "lb" -> 453.592
            "ml" -> 1.0
            "l" -> 1000.0
            "fl oz" -> 29.5735
            "gal" -> 3785.41
            else -> 1.0  // Default for "count" or unknown units
        }
        return price / (quantity * conversionFactor)
    }
}