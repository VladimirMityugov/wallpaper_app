package com.chockydevelopment.wallpaperapp.presentation.util

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember

@Composable
fun BackHandler(
    onBackPressedDispatcher: OnBackPressedDispatcher,
    onBack: () -> Unit
) {
    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBack()
            }
        }
    }

    DisposableEffect(key1 = onBackPressedDispatcher) {
        onBackPressedDispatcher.addCallback(backCallback)

        onDispose {
            backCallback.remove()
        }
    }
}