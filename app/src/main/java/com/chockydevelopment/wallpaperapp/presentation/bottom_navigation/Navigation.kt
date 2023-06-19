package com.chockydevelopment.wallpaperapp.presentation.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = BottomNavItem.Categories.screen_route ){

    }
}