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

        val units = arrayOf("g", "kg", "oz", "lb", "ml", "l", "fl oz", "gal", "count")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        unitSpinner.adapter = adapter

        historyAdapter = HistoryAdapter()
        historyList.adapter = historyAdapter
        historyList.layoutManager = LinearLayoutManager(this)

        val db = AppDatabase.getDatabase(this)
        calculationDao = db.calculationDao()

        lifecycleScope.launch {
            calculationDao.getAll().collect { calculations ->
                historyAdapter.submitList(calculations)
            }
        }

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

                lifecycleScope.launch {
                    calculationDao.insert(calculation)
                }
            }
        }
    }
}