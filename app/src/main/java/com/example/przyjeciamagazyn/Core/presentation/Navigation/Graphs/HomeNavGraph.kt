package com.example.przyjeciamagazyn.Core.presentation.Navigation.Graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Screen
import com.example.przyjeciamagazyn.Home.HomeScreen

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    composable(route = Screen.HomeScreen.route) {
        HomeScreen { route -> navController.navigate(route) }
    }
}
