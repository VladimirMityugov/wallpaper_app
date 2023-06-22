package com.chockydevelopment.wallpaperapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
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
import com.chockydevelopment.wallpaperapp.presentation.bottom_navigation.BottomNavigation
import com.chockydevelopment.wallpaperapp.presentation.bottom_navigation.Navigation
import com.chockydevelopment.wallpaperapp.presentation.theme.AppTheme
import com.chockydevelopment.wallpaperapp.presentation.view_models.CategoriesViewModel
import com.chockydevelopment.wallpaperapp.presentation.view_models.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    private lateinit var binding: ActivityMainBinding
//    private lateinit var navController: NavController
//    private lateinit var appBarConfiguration: AppBarConfiguration
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        supportActionBar?.hide()
//        val navView: BottomNavigationView = binding.navView
//
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
//        navController = navHostFragment.navController
//
//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_collections, R.id.navigation_settings, R.id.navigation_favorites
//            )
//        )
//
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
//
//        navView.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.navigation_collections -> navController.navigate(R.id.action_global_navigation_collections)
//                R.id.navigation_settings -> navController.navigate(R.id.action_global_navigation_settings)
//                R.id.navigation_favorites -> navController.navigate(R.id.navigation_favorites)
//            }
//            true
//        }
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }

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