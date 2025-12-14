package com.example.unitcalculator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

/**
 * Room database for storing calculation history.
 *
 * Uses singleton pattern to ensure single database instance.
 */
@Database(entities = [Calculation::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun calculationDao(): CalculationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Gets or creates the database instance.
         * Thread-safe via synchronized block.
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "calculation_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

/**
 * Type converters for Room to handle Date objects.
 * Converts between Date and Long (timestamp in milliseconds).
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}