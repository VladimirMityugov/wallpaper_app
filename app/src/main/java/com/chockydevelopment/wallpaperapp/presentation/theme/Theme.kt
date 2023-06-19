package com.chockydevelopment.wallpaperapp.presentation.theme

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import com.chockydevelopment.wallpaperapp.presentation.theme.*

@SuppressLint("ConflictingOnColor")
private val LightThemeColors = lightColors(
    primary = grey1,
    primaryVariant = grey2,
    onPrimary = White,
    secondary = grey3,
    secondaryVariant = Black1,
    onSecondary = Black1,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = Black1,
    onBackground = Black1,
    surface = Black1,
    onSurface = Black2
)

@SuppressLint("ConflictingOnColor")
private val DarkThemeColors = darkColors(
    primary = Black1,
    primaryVariant = Black2,
    onPrimary = White,
    secondary = Black1,
    onSecondary = White,
    error = RedErrorLight,
    background = Black,
    onBackground = White,
    surface = Black1,
    onSurface = White,
)

@Composable
fun AppTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
    ){
        content()
    }
}