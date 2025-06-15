# 🧠 MindfulMe

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://android.com)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue.svg)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-orange.svg)](https://developer.android.com/jetpack/compose)
[![Material 3](https://img.shields.io/badge/Design-Material%203-purple.svg)](https://m3.material.io)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

> A modern self-care Android application built with Jetpack Compose that helps users track their daily mood, receive motivational quotes, and visualize their emotional journey over time.

## 📱 Features

### Core Functionality
- **🎯 Daily Mood Tracking** - Log your emotions with intuitive emoji selectors and optional notes
- **💭 Inspirational Quotes** - Receive daily motivational quotes from the Quotable API
- **📊 Progress Visualization** - View mood trends through interactive charts and calendar views
- **⭐ Quote Favorites** - Save and organize your favorite inspirational quotes
- **🔔 Smart Reminders** - Set personalized reminders for daily mood check-ins

### User Experience
- **🎨 Material 3 Design** - Modern, accessible interface following latest design guidelines
- **📱 Responsive Navigation** - Seamless bottom navigation with proper state management
- **🌙 Offline Support** - Full functionality without internet connection
- **📈 Data Insights** - Monthly summaries and mood distribution analytics

## 🏗️ Architecture

MindfulMe follows **Clean Architecture** principles with clear separation of concerns:

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Presentation  │────│     Domain      │────│      Data       │
│                 │    │                 │    │                 │
│ • UI Components │    │ • Models        │    │ • Repositories  │
│ • ViewModels    │    │ • Use Cases     │    │ • Data Sources  │
│ • Navigation    │    │ • Interfaces    │    │ • API/Database  │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Tech Stack

| Category | Technology | Purpose |
|----------|------------|---------|
| **UI Framework** | Jetpack Compose | Modern declarative UI |
| **Architecture** | MVVM + Clean Architecture | Separation of concerns |
| **Navigation** | Navigation Compose | Type-safe navigation |
| **Database** | Room | Local data persistence |
| **Networking** | Retrofit + OkHttp | API communication |
| **Dependency Injection** | Hilt | Dependency management |
| **Async Processing** | Coroutines + Flow | Reactive programming |
| **Testing** | JUnit + Compose Testing | Unit & UI testing |

## 🚀 Getting Started

### Prerequisites

- **Android Studio** Arctic Fox (2020.3.1) or later
- **Android SDK** API level 24 (Android 7.0) or higher
- **Kotlin** 1.8.0 or later
- **JDK** 8 or higher

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/jabbar-app/mindfulme-android.git
   cd mindfulme
   ```

2. **Open in Android Studio**
    - Launch Android Studio
    - Select "Open an existing project"
    - Navigate to the cloned directory

3. **Sync dependencies**
   ```bash
   ./gradlew build
   ```

4. **Run the application**
    - Connect an Android device or start an emulator
    - Click the "Run" button or use `Shift + F10`

### Configuration

The app works out of the box with default settings. For advanced configuration:

1. **API Configuration** (Optional)
   ```kotlin
   // data/remote/api/QuoteApi.kt
   companion object {
       const val BASE_URL = "https://api.quotable.io/"
   }
   ```

2. **Database Configuration** (Optional)
   ```kotlin
   // di/DatabaseModule.kt
   Room.databaseBuilder(context, AppDatabase::class.java, "mindfulme_database")
   ```

## 📂 Project Structure

```
app/src/main/java/id/jabbar/mindfulme/
├── 📁 data/                     # Data layer
│   ├── 📁 local/               # Local data sources
│   │   ├── 📁 database/        # Room database
│   │   ├── 📁 entities/        # Database entities
│   │   └── 📁 dao/             # Data access objects
│   ├── 📁 remote/              # Remote data sources
│   │   ├── 📁 api/             # API interfaces
│   │   └── 📁 dto/             # Data transfer objects
│   └── 📁 repository/          # Repository implementations
├── 📁 domain/                  # Domain layer
│   ├── 📁 model/               # Domain models
│   ├── 📁 repository/          # Repository interfaces
│   └── 📁 usecase/             # Business logic
├── 📁 presentation/            # Presentation layer
│   ├── 📁 components/          # Reusable UI components
│   ├── 📁 home/                # Home screen
│   ├── 📁 mood/                # Mood tracking screens
│   ├── 📁 quotes/              # Quote archive screens
│   ├── 📁 settings/            # Settings screens
│   └── 📁 theme/               # App theming
├── 📁 navigation/              # Navigation setup
├── 📁 di/                      # Dependency injection
└── 📁 utils/                   # Utility classes
```

## 🎯 Key Features Deep Dive

### Navigation Architecture

The app uses **Navigation Compose** with type-safe routing:

```kotlin
// Centralized route definitions
object Routes {
    const val HOME = "home"
    const val MOOD_TRACKER = "mood_tracker"
    const val MOOD_DETAIL = "mood_detail/{date}"
    
    fun moodDetail(date: String) = "mood_detail/$date"
}

// Navigation with parameters
composable(
    route = Routes.MOOD_DETAIL,
    arguments = listOf(navArgument("date") { type = NavType.StringType })
) { backStackEntry ->
    val date = backStackEntry.arguments?.getString("date") ?: ""
    MoodDetailScreen(navController, date)
}
```

### State Management

Reactive state management using **StateFlow** and **Compose State**:

```kotlin
class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    // Reactive UI updates
    fun loadTodayQuote() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            // Business logic...
        }
    }
}
```

### Database Schema

Clean **Room** database design:

```kotlin
@Entity(tableName = "moods")
data class MoodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,        // yyyy-MM-dd format
    val moodLevel: Int,      // 1-5 scale
    val note: String,
    val timestamp: Long
)
```

## 🧪 Testing

### Running Tests

```bash
# Unit tests
./gradlew test

# Instrumented tests
./gradlew connectedAndroidTest

# UI tests
./gradlew connectedDebugAndroidTest
```

### Testing Strategy

| Test Type | Coverage | Framework |
|-----------|----------|-----------|
| **Unit Tests** | ViewModels, Repositories, Use Cases | JUnit, Mockito |
| **Integration Tests** | Database operations | Room Testing |
| **UI Tests** | User interactions, Navigation | Compose Testing |
| **End-to-End** | Complete user journeys | Espresso |

### Example Test

```kotlin
@Test
fun `saveMood updates database and navigates back`() = runTest {
    // Given
    val mood = MoodLevel.HAPPY
    viewModel.selectMood(mood)
    
    // When
    var navigationCalled = false
    viewModel.saveMood { navigationCalled = true }
    
    // Then
    assertTrue(navigationCalled)
    verify(moodRepository).insertMood(any())
}
```

## 🔧 Development

### Code Style

The project follows **Kotlin coding conventions** and **Android best practices**:

- **ktlint** for code formatting
- **Detekt** for static analysis
- **Material 3** design guidelines
- **Accessibility** considerations

### Git Workflow

```bash
# Feature development
git checkout -b feature/new-feature
git commit -m "feat: add new feature"
git push origin feature/new-feature

# Bug fixes
git checkout -b fix/bug-description
git commit -m "fix: resolve bug description"
```

### Performance Optimization

- **Database indexing** for efficient queries
- **Compose performance** using `remember` and `derivedStateOf`
- **Image optimization** with Coil
- **ProGuard** configuration for release builds

## 📱 Screenshots

| Home Screen | Mood Tracker | History View |
|-------------|--------------|--------------|
| ![Home](screenshots/home.png) | ![Tracker](screenshots/tracker.png) | ![History](screenshots/history.png) |

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'feat: add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

### Contribution Guidelines

- Follow the existing code style
- Add tests for new features
- Update documentation as needed
- Ensure all tests pass before submitting

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2024 MindfulMe

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

## 🙏 Acknowledgments

- **[Quotable API](https://quotable.io)** for providing inspirational quotes
- **[Material Design](https://material.io)** for design guidelines
- **[Jetpack Compose](https://developer.android.com/jetpack/compose)** community for examples and best practices
- **Open Source** contributors who make projects like this possible

## 📞 Support

- **Documentation**: [Project Wiki](https://github.com/jabbar-app/mindfulme-android/wiki)
- **Issues**: [GitHub Issues](https://github.com/jabbar-app/mindfulme-android/issues)
- **Discussions**: [GitHub Discussions](https://github.com/jabbar-app/mindfulme-android/discussions)
- **Email**: support@mindfulme.app

---

<div align="center">

**Built with ❤️ using Jetpack Compose**

[⭐ Star this project](https://github.com/jabbar-app/mindfulme-android) if you find it helpful!

</div>