package com.kronos.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = dark00,
    onPrimary = dark10,
    background = dark90,
    error = error,
    outline = divider,
    secondary = dark20,
    onSecondary = dark40,
    tertiary = dark50,
    //tertiary = Purple40,
    onTertiary = dark85,
    surface = dark30,
    surfaceVariant = darkLibre,
    onSurfaceVariant = darkOcupada
    //onSurface = dark10,


)

private val LightColorScheme = lightColorScheme(

    background = Color(0xFFFFFBFE),
    surface = Color(0xFFCE24A3),
    onPrimary = Color.Red,
    onSecondary = Color.Red,
    onTertiary = Color.Red,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),

    )

@Composable
fun KronosTheme(
    isDarkThemeActive: Boolean = true,//isSystemInDarkTheme(),
    useDynamicColor: Boolean = true,// Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        useDynamicColor -> {
            if (isDarkThemeActive) DarkColorScheme else LightColorScheme
        }
        isDarkThemeActive -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}