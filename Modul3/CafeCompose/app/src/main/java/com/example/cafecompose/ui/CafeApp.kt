package com.example.cafecompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cafecompose.ui.screen.DetailScreen
import com.example.cafecompose.ui.screen.HomeScreen

@Composable
fun CafeApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable(
            route = "detail/{name}/{desc}/{image}/{rating}",
            arguments = listOf(navArgument("image") { type = NavType.IntType })
        ) { backStackEntry ->
            val args = backStackEntry.arguments
            DetailScreen(
                name = args?.getString("name") ?: "",
                desc = args?.getString("desc") ?: "",
                image = args?.getInt("image") ?: 0,
                rating = args?.getString("rating") ?: "",
                onBack = { navController.popBackStack() }
            )
        }
    }
}