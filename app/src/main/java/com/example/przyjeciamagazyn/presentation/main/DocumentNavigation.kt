package com.example.przyjeciamagazyn.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.przyjeciamagazyn.data.repository.sampleContractors
import com.example.przyjeciamagazyn.data.repository.sampleDocument
import com.example.przyjeciamagazyn.data.repository.sampleDocuments
import com.example.przyjeciamagazyn.presentation.contractor.ContractorListScreen
import com.example.przyjeciamagazyn.presentation.document.DocumentListScreen
import com.example.przyjeciamagazyn.presentation.document.DocumentPositionDetailScreen
import com.example.przyjeciamagazyn.presentation.document.DocumentDetailScreen
import com.example.przyjeciamagazyn.presentation.document.HomeScreen

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
    data object DocumentListScreen : Screen("document_list_screen")
    data object DocumentDetailScreen : Screen("document_detail_screen")
    data object DocumentPositionDetailScreen : Screen("document_position_detail_screen")
    data object ContractorListScreen : Screen("contractor_list_screen")
}

//TODO: sampleData
@Composable
fun DocumentNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(){ route -> navController.navigate(route) }
        }
        composable(route = Screen.DocumentListScreen.route) {
            DocumentListScreen(sampleDocuments) { route -> navController.navigate(route) }
        }
        composable(route = Screen.DocumentDetailScreen.route) {
            DocumentDetailScreen(sampleDocument) { route -> navController.navigate(route) }
        }
        composable(route = Screen.DocumentPositionDetailScreen.route) {
            DocumentPositionDetailScreen(sampleDocument.positions[0])
        }
        composable(route = Screen.ContractorListScreen.route) {
            ContractorListScreen(sampleContractors)
        }
    }
}