package com.chockydevelopment.wallpaperapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.chockydevelopment.wallpaperapp.presentation.bottom_navigation.BottomNavigation
import com.chockydevelopment.wallpaperapp.presentation.bottom_navigation.Navigation
import com.chockydevelopment.wallpaperapp.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(darkTheme = false) {
                Surface(color = MaterialTheme.colors.primary) {
                    MainScreenView()
                }
            }
        }
    }


    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun MainScreenView(){
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { BottomNavigation(navController = navController) }
        ) {
            Navigation(navController = navController)
        }
    }


}