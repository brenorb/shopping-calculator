# Product Brief: Unit Price Calculator

## Project Overview

A local-only Android app that calculates per-unit prices for shopping items, enabling users to compare value across different package sizes and units. The app stores calculation history with timestamps for manual comparison during shopping trips.

## Target Audience

Budget-conscious shoppers who want to determine the best value when comparing products with different quantities, weights, or volumes (e.g., 500g vs 1kg packages, 6-pack vs 12-pack).

## Primary Benefits / Features

- **Quick Per-Unit Calculation**: Input item price and quantity with unit to get price per unit
- **Multi-Unit Support**: Handles various units (weight: g/kg/lb/oz, volume: ml/L/fl oz, count: items)
- **Calculation History**: Timestamped history of all calculations for manual comparison
- **Offline-First**: Fully local, no internet required, works anywhere
- **Fast & Simple**: Minimal UI focused on speed during active shopping

## Tech Stack & Architecture

- **Language**: Kotlin (Android standard, concise, null-safe)
- **UI**: XML layouts with Material Design components
- **Database**: Room (SQLite wrapper for local storage)
- **Architecture**: MVVM with Android Architecture Components
- **Min SDK**: API 24 (Android 7.0) for broad compatibility
- **Target SDK**: API 33 (Android 13)
- **Build**: Gradle with Kotlin DSL

No backend, authentication, or network calls required. Pure client-side application with local persistence.
