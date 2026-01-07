# 🎬 Flix

A modern Android streaming app built with Jetpack Compose and Material Design 3, featuring a
beautiful dark theme UI for browsing and discovering movies and TV shows.

## ✨ Features

- **Modern UI/UX**: Clean and intuitive interface with Material Design 3
- **Dark Theme**: Eye-friendly dark color scheme optimized for viewing
- **Search Functionality**: Quick search with custom filter options
- **Responsive Design**: Smooth animations and touch interactions
- **Personalized Experience**: Welcome back greeting with profile picture

## 🛠️ Tech Stack

- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern declarative UI toolkit
- **Material Design 3** - Latest Material Design components
- **Android Gradle** - Build system
- **Compose Navigation** - Screen navigation

### Key Dependencies

- Compose BOM 2024.09.00
- Material3 1.4.0
- Material Icons Extended 1.7.5
- Activity Compose 1.12.2
- Kotlin 2.0.21

## 📱 Screenshots

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

## 📝 Current Status

This project is currently in active development. The home screen with search functionality is
implemented, with more features coming soon.

### Planned Features

- Movie/TV show listings
- Detailed view pages
- Favorites and watchlist
- API integration
- Video playback
- User authentication

---

## 🔧 Bug Fixes & Improvements

### Search Functionality Fix Summary

#### Issues Fixed

1. **Search Not Working**
    - **Problem**: SearchField was creating its own ViewModel instance instead of sharing with
      SearchScreen
    - **Solution**: Modified SearchField to accept `query` and `onQueryChange` parameters, removing
      the internal ViewModel instance

2. **Missing UI States**
    - **Added Loading State**: Shows CircularProgressIndicator when `isLoading` is true
    - **Added Empty State**: Shows "Search for a movie!" when query is empty
    - **Added No Results State**: Shows "No results found" with helper text when search returns no
      results

3. **API Endpoint Fix**
    - **Changed**: `/search/movie` → `search/movie` (removed leading slash)
    - **Reason**: The base URL already includes the version path, leading slash causes double path

4. **Data Model Improvements**
    - Made `poster_path` and `backdrop_path` nullable in Result model
    - Added null handling for poster images in UI

5. **Performance Optimization**
    - Added 500ms debouncing to search using LaunchedEffect
    - Prevents excessive API calls while user is typing

6. **Navigation Issue Fix**
    - **Problem**: Rapidly pressing the back button multiple times caused white screen
    - **Solution**: Added click debouncing and state management in MovieScreen
    - Prevents multiple navigation events from being triggered simultaneously
    - Added `isNavigating` state flag with 500ms cooldown period

#### Files Modified

1. **SearchScreen.kt**
    - Added query state management with `remember { mutableStateOf("") }`
    - Added LaunchedEffect for debouncing
    - Implemented proper UI states (loading, empty, no results, results)
    - Added null check for `poster_path` with placeholder
    - Fixed imports

2. **SearchField.kt**
    - Removed internal ViewModel instance
    - Added `query` and `onQueryChange` parameters
    - Component now acts as a controlled input

3. **SearchApi.kt**
    - Fixed endpoint path from `/search/movie` to `search/movie`

4. **Result.kt** (data model)
    - Made `poster_path` and `backdrop_path` nullable (`String?`)

5. **MovieScreen.kt**
    - Added click debouncing with `isNavigating` state
    - Added coroutine scope for delay management
    - Disabled button during navigation to prevent rapid clicks
    - Added 500ms cooldown between navigation events

6. **All Search & Movie Module Files**
    - Fixed package import paths after project restructuring
    - Removed `app.` prefix from package names
    - Updated: SearchApi, SearchViewModel, SearchUiState, SearchRepository, MovieViewModel,
      MovieUiState, MovieRepository, and all related files

#### How It Works Now

1. User types in SearchField
2. Query state updates immediately (instant UI feedback)
3. LaunchedEffect waits 500ms (debounce)
4. If query is not empty, calls `searchViewModel.searchMovie(query)`
5. While loading, shows CircularProgressIndicator
6. When results arrive:
    - If empty: Shows "No results found" message
    - If not empty: Displays movie list with images and details
7. If user clears search: Shows "Search for a movie!" prompt
8. Back button navigation is protected against rapid clicks

#### UI States

- **Empty**: Query is empty → "Search for a movie!"
- **Loading**: `isLoading = true` → CircularProgressIndicator
- **No Results**: `isLoading = false` && results empty → "No results found"
- **Has Results**: `isLoading = false` && results not empty → Movie list

#### Testing Recommendations

1. Test with various search queries
2. Verify debouncing works (API not called on every keystroke)
3. Check loading state appears briefly
4. Verify "No results found" shows for invalid queries
5. Test with movies that have no poster images
6. Clear search and verify empty state returns
7. Test back button by clicking multiple times rapidly
8. Verify no white screen appears during navigation

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

