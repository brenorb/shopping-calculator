# Architecture Documentation

## Overview

The Unit Price Calculator follows MVVM architecture principles with Room database for local persistence. The app is entirely offline with no network dependencies.

## Architecture Pattern

### MVVM Components

**Model**: 
- `Calculation.kt` - Room entity for calculation data
- `AppDatabase.kt` - Room database singleton
- `CalculationDao.kt` - Data access interface

**View**:
- XML layouts in `res/layout/`
- `MainActivity.kt` handles UI bindings and user interactions

**ViewModel** (implicit):
- Room's Flow automatically provides reactive data updates
- `lifecycleScope` in MainActivity manages coroutine lifecycle

## Data Flow

```
User Input → MainActivity → UnitConverter → Calculation Entity
                ↓                                     ↓
          CalculationDao.insert()          Room Database
                                                      ↓
                                           Flow<List<Calculation>>
                                                      ↓
                                              HistoryAdapter
                                                      ↓
                                                 RecyclerView
```

## Key Components

### UnitConverter

Handles unit normalization and price calculation logic. All units are converted to base units:
- Weight → grams (g)
- Volume → milliliters (ml)
- Count → items

**Conversion Factors**:
```kotlin
"kg" → 1000.0 (1 kg = 1000g)
"oz" → 28.35 (1 oz ≈ 28.35g)
"lb" → 453.592 (1 lb ≈ 453.592g)
"l" → 1000.0 (1 L = 1000ml)
"fl oz" → 29.5735 (1 fl oz ≈ 29.57ml)
"gal" → 3785.41 (1 gal ≈ 3785.41ml)
```

Formula: `price / (quantity * conversionFactor)`

### Room Database

**Entity**: `Calculation`
- Stores item details, price, quantity, unit, calculated per-unit price, and timestamp
- Auto-generated primary key

**DAO**: `CalculationDao`
- `insert()` - Add new calculation
- `getAll()` - Returns Flow of all calculations, ordered by timestamp DESC

**TypeConverters**: `Converters`
- Converts `Date` ↔ `Long` for Room storage

### MainActivity Lifecycle

1. **onCreate()**: 
   - Initialize views and adapters
   - Set up Room database connection
   - Start observing calculation Flow
   - Set up calculate button listener

2. **User Action**:
   - Input validation
   - Calculate per-unit price
   - Create Calculation entity
   - Insert into database via coroutine

3. **Automatic Update**:
   - Room Flow emits updated list
   - RecyclerView updates automatically

## Threading Model

- **Main Thread**: UI operations, view bindings
- **IO Thread**: Database operations (handled by Room internally)
- **Coroutines**: Used via `lifecycleScope` for async database operations

## Data Persistence

All data is stored locally in SQLite database via Room:
- Database name: `calculation_database`
- Location: App's internal storage
- No cloud sync or backup
- Data persists across app restarts
- Cleared when app is uninstalled

## Dependencies

### Core
- `androidx.core:core-ktx` - Kotlin extensions
- `androidx.appcompat:appcompat` - Compatibility library
- `com.google.android.material:material` - Material Design components

### Database
- `androidx.room:room-runtime` - Room database runtime
- `androidx.room:room-ktx` - Kotlin extensions for Room
- `androidx.room:room-compiler` - Annotation processor (kapt)

### Lifecycle
- `androidx.lifecycle:lifecycle-viewmodel-ktx` - ViewModel with coroutines
- `androidx.lifecycle:lifecycle-runtime-ktx` - Lifecycle-aware components

## Build Configuration

- **Min SDK**: 24 (Android 7.0 Nougat)
- **Target SDK**: 33 (Android 13)
- **Compile SDK**: 33
- **JVM Target**: 1.8
- **Build Tool**: Gradle with Kotlin DSL

## Future Considerations

Potential enhancements:
- Export history to CSV
- Clear history function
- Edit/delete individual calculations
- Category tagging for items
- Chart/graph view of price comparisons
- Dark mode theme
