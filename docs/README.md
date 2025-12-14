# Documentation Index

## For Users

- **[User Guide](USER_GUIDE.md)** - How to use the app, supported units, and examples

## For Developers

- **[Product Brief](PRODUCT_BRIEF.md)** - Project overview, target audience, and tech stack
- **[Architecture](ARCHITECTURE.md)** - Technical architecture, data flow, and implementation details

## Quick Links

- [Main README](../README.md) - Project overview and quick start
- [Source Code](../app/src/main/java/com/example/unitcalculator/) - Implementation

## API Reference

Key classes and their purpose:

| Class | Purpose |
|-------|---------|
| `MainActivity` | Main UI and user interaction handling |
| `Calculation` | Data model for price calculations |
| `CalculationDao` | Database access interface |
| `AppDatabase` | Room database singleton |
| `UnitConverter` | Unit normalization and price calculation logic |
| `HistoryAdapter` | RecyclerView adapter for calculation history |

See inline code comments for detailed API documentation.
