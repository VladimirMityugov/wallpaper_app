package com.chockydevelopment.wallpaperapp.presentation.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chockydevelopment.wallpaperapp.presentation.composable_screens.*

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.Categories.screen_route) {

        composable(route = Screen.Categories.screen_route) {
            CategoriesScreen(navController = navController)
        }

        composable(route = Screen.Collection.screen_route + "/{collectionId}",
            arguments = listOf(
                navArgument("collectionId") {
                    nullable = true
                    type = NavType.StringType
                }
            )
        ) { entry ->
            entry.arguments?.getString("collectionId")
                ?.let { CollectionScreen(collectionId = it, navController = navController) }
        }

        composable(route = Screen.Image.screen_route + "/{imageId}",
            arguments = listOf(
                navArgument("imageId") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { entry ->
            ImageScreen(imageId = entry.arguments?.getString("imageId"), navController = navController)
        }

        composable(route = Screen.Favorites.screen_route) {
            FavoritesScreen(navController = navController)
        }

        composable(route = Screen.Settings.screen_route) {
            SettingsScreen(navController = navController)
        }
    }
}