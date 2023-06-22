package com.chockydevelopment.wallpaperapp.presentation.bottom_navigation

import com.chockydevelopment.wallpaperapp.R

sealed class Screen(var title:String, var icon:Int, var screen_route:String){

    object Categories : Screen("Categories", R.drawable.collection,"categories")
    object Settings : Screen("Settings", R.drawable.settings,"settings")
    object Favorites : Screen("Favorites", R.drawable.heart,"favorites")
    object Collection : Screen("Collection", R.drawable.collection,"collection")
    object Image : Screen("Image", R.drawable.collection,"image")

    fun withArgs(vararg args: String):String{
        return buildString {
            append(screen_route)
            args.forEach { arg ->
                append("/$arg")
            }
        }

    }

}
