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

