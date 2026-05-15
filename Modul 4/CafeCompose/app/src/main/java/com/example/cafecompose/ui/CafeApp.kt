package com.example.cafecompose.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cafecompose.factory.CafeViewModelFactory
import com.example.cafecompose.ui.screen.DetailScreen
import com.example.cafecompose.ui.screen.HomeScreen
import com.example.cafecompose.ui.viewmodel.CafeViewModel

@Composable
fun CafeApp() {

    val navController = rememberNavController()

    val viewModel: CafeViewModel = viewModel(
        factory = CafeViewModelFactory("Cafe Compose")
    )

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {

            HomeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(
            route = "detail/{name}/{desc}/{image}/{rating}",
            arguments = listOf(
                navArgument("image") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val args = backStackEntry.arguments

            DetailScreen(
                name = args?.getString("name") ?: "",
                desc = args?.getString("desc") ?: "",
                image = args?.getInt("image") ?: 0,
                rating = args?.getString("rating") ?: "",
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
