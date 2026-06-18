package com.example.simpsonsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.*
import androidx.compose.*
import com.example.simpsonsapp.detail.DetailScreen
import com.example.simpsonsapp.main.MainScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                onNavigateToDetail = { episodeId ->
                    navController.navigate("detail/$episodeId")
                }
            )
        }
        composable(
            route = "detail/{episodeId}",
            arguments = listOf(navArgument("episodeId") { type = NavType.IntType })
        ) {
            DetailScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
