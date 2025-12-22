package com.example.flix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.flix.app.home.presentation.view.screen.HomeScreen
import com.example.flix.ui.theme.FlixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlixTheme(
                darkTheme = true,
                dynamicColor = false
            ) {
                HomeScreen()

            }
        }
    }
}
