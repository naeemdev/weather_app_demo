# Jetpack Compose WeatherApp 🌡

WeatherApp is an application that shows you the weather according to enter city.

<br/>

You can enter your API key in the location specified in the Constants.kt file in the utlis (Constants.kt) folder.




## Api 📦
* [OpenWeather forecast data](https://openweathermap.org/)


### Search City Screen 🔍

○ A text input to enter a city name.
○ A "Search" button.
○ A card or section to display:
■ City name
■ Current temperature
■ Weather condition (e.g., "Cloudy", "Rainy")
■ An icon representing the weather (optional, bonus)

🏗️ Tech Stack

    Kotlin - Primary language
    Jetpack Compose
    Hilt - Dependency Injection
    Retrofit - Networking
    OkHttp - HTTP client
    Coil - Image Loading
    Coroutines + Flow - Async programming
    DataStore - Local persistence for city
    Material 3 - UI Components
    follows SOLID, Clean Architecture, and Jetpack Compose best practices

## Project Structure 🏗

```
com/
└── naeemdev/
    └── weather/
        ├── data/                         # Data layer: manages network, local data, and mapping
        │   ├── mapper/                   # DTO <-> Domain model mappers
        │   ├── remote/                   # Retrofit interfaces and API-related logic
        │   ├── repositories/             # Repository implementations (fetching from API/Datastore)
        │   └── response/                 # Network response models (DTOs)
        │
        ├── di/                           # Dependency Injection modules (used by Hilt)
        │   ├── AppModule                 # Provides app-level dependencies
        │   ├── DataStoreModule           # Provides DataStore (e.g., for user prefs)
        │   ├── DispatcherModule          # Provides Coroutine Dispatchers
        │   └── RepositoryModule          # Provides repository bindings to interfaces
        │
        ├── domain/                       # Domain layer: clean business logic
        │   ├── model/                    # Core business models (used across layers)
        │   ├── repositories/             # Repository interfaces (abstraction)
        │   ├── usecases/                 # Use case classes encapsulating business rules
        │   ├── ErrorHandler              # Centralized error handling logic
        │   ├── ErrorType                 # Enum or sealed class for known error categories
        │   └── Resource                  # Wrapper class for success and error states
        │
        ├── presentation/                # UI and ViewModel layer (MVVM)
        │   └── component/
        │       ├── SearchCityScreen.kt   # Compose screen to search and display city weather
        │       ├── SearchCityState       # UI state holder for SearchCityScreen
        │       └── SearchCityViewModel   # Handles UI logic, exposes state and events
        │
        ├── ui/                           # Shared UI components, theme, color palette, etc.
        │
        └── utils/                        # Utility/helper classes used across app
            ├── AppCoroutineDispatchers   # Abstracted dispatchers for testability
            ├── Constants.kt              # App-wide constants (API key, endpoints, etc.)
            ├── HourConverter             # Helper to convert timestamps into hours
            └── WeatherType               # Mapper or utility for weather type display
        │
        └── MainActivity                  # App’s entry point (hosting Compose screens)

```

🚀 How to Run the App

1️⃣ Download the code
2️⃣ Open in Android Studio : Sync Gradle and install dependencies
Open the project in Android Studio Android Studio Narwhal | 2025.1.1 Patch 1 or newer.
Ensure you have an Android Emulator or a Physical Device connected.
3️⃣ Run the App
./gradlew build && ./gradlew installDebug
Or, simply press Run ▶️ in Android Studio.


🎯 Future Enhancements

- Add caching mechanism to reduce redundant API calls and support offline mode.
- Save recently searched cities in Room DB with a timestamp.
- Store daily forecast data locally for the last N days. 
- Add light/dark theme toggle. 
- Add support for multiple languages (i18n). 
- Integrate location services to auto-detect user's current city.
- Custom animations for weather transitions (e.g., rain animation, snow falling). 
- Display charts/graphs for temperature trends using Jetpack Compose (e.g., using Recharts or Canvas).
- Daily weather notifications (morning forecast summary).
- Allow temperature unit switching (°C/°F).
- Enable forecast duration settings (e.g., 3-day, 7-day).
- Custom refresh intervals for auto-update.
