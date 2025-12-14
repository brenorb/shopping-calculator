package com.example.unitcalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class HistoryAdapter : ListAdapter<Calculation, HistoryAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.itemName)
        val perUnitPrice: TextView = view.findViewById(R.id.perUnitPrice)
        val timestamp: TextView = view.findViewById(R.id.timestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val calculation = getItem(position)
        holder.itemName.text = calculation.itemName

        val baseUnit = when (calculation.unit) {
            "g", "kg", "oz", "lb" -> "g"
            "ml", "l", "fl oz", "gal" -> "ml"
            else -> "unit"
        }

        holder.perUnitPrice.text = String.format("%.4f / %s", calculation.perUnitPrice, baseUnit)
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        holder.timestamp.text = sdf.format(calculation.timestamp)
    }
}

class DiffCallback : DiffUtil.ItemCallback<Calculation>() {
    override fun areItemsTheSame(oldItem: Calculation, newItem: Calculation): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Calculation, newItem: Calculation): Boolean {
        return oldItem == newItem
    }
}