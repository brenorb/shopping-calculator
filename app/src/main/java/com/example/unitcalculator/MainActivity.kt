package com.example.unitcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import java.util.Date

/**
 * Main activity for the Unit Price Calculator app.
 * 
 * Provides UI for:
 * - Entering item details (name, price, quantity, unit)
 * - Calculating per-unit prices
 * - Viewing calculation history with timestamps
 */
class MainActivity : AppCompatActivity() {

    private lateinit var itemName: EditText
    private lateinit var price: EditText
    private lateinit var quantity: EditText
    private lateinit var unitSpinner: Spinner
    private lateinit var calculateButton: Button
    private lateinit var historyList: RecyclerView

    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var calculationDao: CalculationDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemName = findViewById(R.id.itemName)
        price = findViewById(R.id.price)
        quantity = findViewById(R.id.quantity)
        unitSpinner = findViewById(R.id.unitSpinner)
        calculateButton = findViewById(R.id.calculateButton)
        historyList = findViewById(R.id.historyList)

        // Set up unit spinner with all supported units
        val units = arrayOf("g", "kg", "oz", "lb", "ml", "l", "fl oz", "gal", "count")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        unitSpinner.adapter = adapter

        // Set up RecyclerView for calculation history
        historyAdapter = HistoryAdapter()
        historyList.adapter = historyAdapter
        historyList.layoutManager = LinearLayoutManager(this)

        // Initialize database
        val db = AppDatabase.getDatabase(this)
        calculationDao = db.calculationDao()

        // Observe calculation history from database
        lifecycleScope.launch {
            calculationDao.getAll().collect { calculations ->
                historyAdapter.submitList(calculations)
            }
        }

        // Handle calculate button click
        calculateButton.setOnClickListener {
            val name = itemName.text.toString()
            val priceValue = price.text.toString().toDoubleOrNull()
            val quantityValue = quantity.text.toString().toDoubleOrNull()

            if (name.isNotEmpty() && priceValue != null && quantityValue != null) {
                val unit = unitSpinner.selectedItem.toString()
                val perUnitPrice = UnitConverter.calculatePerUnitPrice(priceValue, quantityValue, unit)

                val calculation = Calculation(
                    itemName = name,
                    price = priceValue,
                    quantity = quantityValue,
                    unit = unit,
                    perUnitPrice = perUnitPrice,
                    timestamp = Date()
                )

                // Save to database (history auto-updates via Flow)
                lifecycleScope.launch {
                    calculationDao.insert(calculation)
                }
            }
        }
    }
}