package com.chockydevelopment.wallpaperapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.chockydevelopment.wallpaperapp.databinding.ActivityMainBinding
import com.chockydevelopment.wallpaperapp.presentation.bottom_navigation.BottomNavItem
import com.chockydevelopment.wallpaperapp.presentation.bottom_navigation.CategoriesScreen
import com.chockydevelopment.wallpaperapp.presentation.bottom_navigation.FavoritesScreen
import com.chockydevelopment.wallpaperapp.presentation.bottom_navigation.SettingsScreen
import com.chockydevelopment.wallpaperapp.presentation.view_models.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val navView: BottomNavigationView = binding.navView

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_collections, R.id.navigation_settings, R.id.navigation_favorites
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_collections -> navController.navigate(R.id.action_global_navigation_collections)
                R.id.navigation_settings -> navController.navigate(R.id.action_global_navigation_settings)
                R.id.navigation_favorites -> navController.navigate(R.id.navigation_favorites)
            }
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MainScreenView()
//        }
//    }
//
//
//    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//    @Composable
//    fun MainScreenView(){
//        val navController = rememberNavController()
//        Scaffold(
//            bottomBar = { BottomNavigation(navController = navController) }
//        ) {
//
//            NavigationGraph(navController = navController)
//        }
//    }
//
//    @Composable
//    fun NavigationGraph(navController: NavHostController) {
//        NavHost(navController, startDestination = BottomNavItem.Categories.screen_route) {
//            composable(BottomNavItem.Categories.screen_route) {
//                CategoriesScreen()
//            }
//            composable(BottomNavItem.Settings.screen_route) {
//                SettingsScreen()
//            }
//            composable(BottomNavItem.Favorites.screen_route) {
//                FavoritesScreen()
//            }
//        }
//    }
//
//    @Composable
//    fun BottomNavigation(navController: NavController) {
//        val items = listOf(
//            BottomNavItem.Categories,
//            BottomNavItem.Settings,
//            BottomNavItem.Favorites
//        )
//        androidx.compose.material.BottomNavigation(
//            backgroundColor = colorResource(id = R.color.teal_200),
//            contentColor = Color.Black
//        ) {
//            val navBackStackEntry by navController.currentBackStackEntryAsState()
//            val currentRoute = navBackStackEntry?.destination?.route
//            items.forEach { item ->
//                BottomNavigationItem(
//                    icon = {
//                        Icon(
//                            painter = painterResource(id = item.icon),
//                            contentDescription = item.title,
//                            modifier = Modifier.size(24.dp)
//                        )
//                    },
//                    selectedContentColor = Color.Blue,
//                    unselectedContentColor = Color.Black.copy(0.4f),
//                    alwaysShowLabel = true,
//                    selected = currentRoute == item.screen_route,
//                    onClick = {
//                        navController.navigate(item.screen_route) {
//
//                            navController.graph.startDestinationRoute?.let { screen_route ->
//                                popUpTo(screen_route) {
//                                    saveState = true
//                                }
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                )
//            }
//        }
//    }
}