package com.chockydevelopment.wallpaperapp.presentation.bottom_navigation

import com.chockydevelopment.wallpaperapp.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Categories : BottomNavItem("Categories", R.drawable.collection,"categories")
    object Settings : BottomNavItem("Settings", R.drawable.settings,"settings")
    object Favorites : BottomNavItem("Favorites", R.drawable.heart,"favorites")

}
