package com.chockydevelopment.wallpaperapp.presentation.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chockydevelopment.wallpaperapp.presentation.composable_screens.*
import com.chockydevelopment.wallpaperapp.presentation.view_models.ImageViewModel

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

        composable(route = Screen.Image.screen_route) {
            val viewModel = hiltViewModel<ImageViewModel>()
            val image by viewModel.image.collectAsStateWithLifecycle()
            image?.let { it1 -> ImageScreen(imageItem = it1) }

        }

        composable(route = Screen.Favorites.screen_route) {
            FavoritesScreen(navController = navController)
        }

        composable(route = Screen.Settings.screen_route) {
            SettingsScreen(navController = navController)
        }
    }
}
