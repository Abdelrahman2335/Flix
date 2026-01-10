# Flix

A simple Android movie browsing application that connects to The Movie Database (TMDB) API to
provide users with an intuitive interface for discovering and exploring movies.

## Overview

Flix is a demo Android application built with modern Android development practices. It demonstrates
the implementation of MVVM architecture, Jetpack Compose for UI, and integration with external APIs.
The app provides a clean interface for browsing movies, viewing detailed information, and watching
trailers directly from YouTube.

## Features

- Browse top-rated and popular movies
- Filter movies by category (Popular, Top Rated, Upcoming, Now Playing)
- Search for any movie by title
- View detailed movie information including:
    - Synopsis and overview
    - Cast and crew information
    - Release date and ratings
    - Original language
    - Genre classification
- Watch movie trailers via integrated YouTube functionality
- Responsive UI with loading states and error handling
- Image loading with Coil library

## Tech Stack

### Core Technologies

- Kotlin 2.0.21
- Android SDK (Min: 24, Target: 36)
- Jetpack Compose (BOM 2024.09.00)
- Material Design 3

### Architecture & DI

- MVVM Architecture Pattern
- Hilt (Dagger) for Dependency Injection
- Kotlin Coroutines for asynchronous operations
- StateFlow for reactive state management

### Networking & Data

- Retrofit 2.9.0 for REST API calls
- OkHttp 4.12.0 with logging interceptor
- Gson for JSON parsing
- TMDB API for movie data

### UI & Navigation

- Jetpack Compose for declarative UI
- Navigation Compose for screen navigation
- Coil for image loading
- Material Icons Extended
- Accompanist Placeholder for loading states

## Architecture

This project follows the MVVM (Model-View-ViewModel) architectural pattern with clear separation of
concerns:

### Project Structure

```
com.example.flix/
├── core/               # Shared utilities and application-level components
│   ├── di/            # Dependency injection modules
│   ├── util/          # Helper classes and utilities
│   └── MainActivity.kt
├── home/              # Home screen feature
│   ├── data/          # Data layer (repositories, data sources)
│   ├── domain/        # Domain layer (models, use cases)
│   └── presentation/  # UI layer (ViewModels, Composables)
├── movie/             # Movie details feature
│   ├── data/
│   ├── domain/
│   └── presentation/
└── search/            # Search feature
    ├── data/
    ├── domain/
    └── presentation/
```

### MVVM Components

- **Model**: Data classes and repository implementations handling API calls and data transformation
- **View**: Jetpack Compose UI components that observe and react to state changes
- **ViewModel**: Business logic layer managing UI state and coordinating data operations

Each feature module (home, movie, search) follows this layered architecture, ensuring
maintainability and testability.

## Getting Started

### Prerequisites

- Android Studio Hedgehog or later
- JDK 11 or higher
- Android device or emulator running API 24+

### API Configuration

1. Obtain a TMDB API key from [The Movie Database](https://www.themoviedb.org/settings/api)
2. Create a `local.properties` file in the project root (if not exists)
3. Add your TMDB auth token:
   ```
   TMDB_AUTH_TOKEN=Bearer YOUR_API_TOKEN_HERE
   ```

### Build and Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/flix.git
   cd flix
   ```

2. Open the project in Android Studio

3. Sync Gradle dependencies

4. Run the app on an emulator or physical device

## App Flow / Screens

### Home Screen

- Displays a list of popular movies in a scrollable card format
- Shows movie poster, title, genre, release date, rating, and language
- Provides category filter options (Popular, Top Rated, Upcoming, Now Playing)
- Each movie card includes a play button to view trailers
- Tapping a movie card navigates to the movie details screen

### Search Screen

- Search bar for entering movie titles
- Real-time search results display
- Results show movie posters and basic information
- Tapping a result navigates to the movie details screen

### Movie Details Screen

- Full movie poster and backdrop
- Comprehensive movie information:
    - Title and tagline
    - Release date and runtime
    - Genres
    - Rating and vote count
    - Overview/synopsis
- Cast and crew section with profile images
- Trailer section with play button
- Back navigation to previous screen

### Trailer Playback

- Clicking the play button fetches the movie trailer from TMDB
- Opens YouTube app if installed, otherwise opens in browser
- Falls back gracefully if no trailer is available

## External Services

### The Movie Database (TMDB) API

The app integrates with TMDB API to fetch:

- Movie listings (popular, top-rated, upcoming, now playing)
- Movie details and metadata
- Cast and crew information
- Movie trailers and videos
- High-quality movie posters and backdrops

Authentication is handled via Bearer token passed in request headers.

### YouTube Integration

- Trailer playback is handled by launching YouTube intents
- The app passes the video key to YouTube for playback
- Supports both YouTube app and web browser fallback
- No embedded player to keep the app lightweight

## Limitations

- This is a demo/learning project and not production-ready
- No offline support or local caching
- No user authentication or personalization features
- No watchlist or favorites functionality
- Limited error handling and retry mechanisms
- No unit tests or UI tests included
- Trailers open externally rather than in-app playback
- Search is title-only (no advanced filters)
- No support for TV shows or other media types
- English language UI only

## License

MIT License

Copyright (c) 2026 Flix

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

*Coming soon*

## 🏗️ Architecture

The project follows clean architecture principles with a modular structure:

```
app/
└── src/main/java/com/example/flix/
    ├── MainActivity.kt
    ├── app/
    │   └── home/
    │       └── presentation/
    │           └── view/
    │               ├── screen/
    │               │   └── HomeScreen.kt
    │               └── component/
    │                   ├── Appbar.kt
    │                   ├── SearchField.kt
    │                   └── HomeScreenContent.kt
    └── ui/theme/
        ├── Color.kt
        ├── Theme.kt
        └── Type.kt
```

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog | 2023.1.1 or newer
- JDK 11 or higher
- Android SDK 24+
- Gradle 8.13.2

### Installation

1. Clone the repository

```bash
git clone https://github.com/abdelrahman2335/Flix.git
```

2. Open the project in Android Studio

3. Sync project with Gradle files

4. Run the app on an emulator or physical device

## 🎨 Color Palette

- **Background**: `#1F1F29`
- **Surface**: `#131316`
- **Text Secondary**: `#B9C1D9`
---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the issues page.

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Author

Abdelrahman Mustafa

- GitHub: [@Abdelrahman2335](https://github.com/yourusername)

## 🙏 Acknowledgments

- [The Movie Database (TMDB)](https://www.themoviedb.org/) for providing the movie data API
- Android Development Team for Jetpack Compose
- Material Design team for the design system
- The Android community

---

**Note**: This product uses the TMDB API but is not endorsed or certified by TMDB.

<img src="https://www.themoviedb.org/assets/2/v4/logos/v2/blue_short-8e7b30f73a4020692ccca9c88bafe5dcb6f8a62a4c6bc55cd9ba82bb2cd95f6c.svg" width="150" alt="TMDB Logo">

---

⭐ Star this repo if you find it helpful!

