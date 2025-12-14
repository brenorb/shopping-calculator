# Unit Price Calculator

A local-only Android app for calculating per-unit prices while shopping. Compare products by their actual unit cost and track your calculations with timestamps.

## Features

- **Per-unit price calculation** across multiple unit types (weight, volume, count)
- **Calculation history** with timestamps for manual comparison
- **Multi-unit support**: g, kg, oz, lb, ml, L, fl oz, gal, count
- **Fully offline** - no internet required, all data stored locally

## Quick Start

### Prerequisites

- Android Studio Hedgehog or later
- Android SDK 24+ (Android 7.0)
- Gradle 8.0+

### Build & Run

```bash
./gradlew assembleDebug
./gradlew installDebug
```

Or open in Android Studio and run directly.

## Tech Stack

- **Language**: Kotlin
- **UI**: XML layouts with Material Design
- **Database**: Room (SQLite)
- **Architecture**: MVVM with Android Architecture Components
- **Min SDK**: API 24 (Android 7.0)

## Project Structure

```
app/src/main/java/com/example/unitcalculator/
├── MainActivity.kt          # Main UI and calculation logic
├── Calculation.kt           # Data model for calculations
├── CalculationDao.kt        # Database access layer
├── AppDatabase.kt           # Room database setup
├── UnitConverter.kt         # Unit conversion logic
└── HistoryAdapter.kt        # RecyclerView adapter for history
```

## How It Works

1. Enter item name, price, and quantity
2. Select the unit from the dropdown
3. Tap Calculate - the per-unit price is computed and saved
4. View history below, sorted by most recent first

The app normalizes all units to a base unit (grams for weight, milliliters for volume) to calculate price per standardized unit for easier comparison.

## License

MIT
