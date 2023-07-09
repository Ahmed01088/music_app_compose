package com.example.musicapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.data.Screen

@Composable
fun Navigation() {
    val navController= rememberNavController()
    NavHost(navController = navController,  startDestination = Screen.Home.route){
        composable(route=Screen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(Screen.Search.route){
            SearchScreen(navController = navController)

        }
        composable(Screen.Library.route){
            LibraryScreen(navController = navController)
        }
    }

}
