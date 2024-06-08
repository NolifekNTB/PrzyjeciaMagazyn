package com.example.przyjeciamagazyn.Core.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorListScreen
import com.example.przyjeciamagazyn.Core.data.sampleContractors
import com.example.przyjeciamagazyn.Core.data.sampleDocuments
import com.example.przyjeciamagazyn.Receipts.presentation.ReceiptDocumentScreen
import com.example.przyjeciamagazyn.Receipts.presentation.DocumentPositionDetailScreen
import com.example.przyjeciamagazyn.Receipts.presentation.DocumentDetailScreen
import com.example.przyjeciamagazyn.Home.HomeScreen

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
    data object ReceiptDocumentScreen : Screen("document_list_screen")
    data object DocumentDetailScreen : Screen("document_detail_screen")
    data object DocumentPositionDetailScreen : Screen("document_position_detail_screen")
    data object ContractorListScreen : Screen("contractor_list_screen")
}

//TODO: sampleData
@Composable
fun NavigationNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(){ route -> navController.navigate(route) }
        }
        composable(route = Screen.ReceiptDocumentScreen.route) {
            ReceiptDocumentScreen() { route -> navController.navigate(route) }
        }
        composable(route = Screen.DocumentDetailScreen.route) {
            DocumentDetailScreen(sampleDocuments[0]) { route -> navController.navigate(route) }
        }
        composable(route = Screen.DocumentPositionDetailScreen.route) {
            DocumentPositionDetailScreen(sampleDocuments[0].positions[0])
        }
        composable(route = Screen.ContractorListScreen.route) {
            ContractorListScreen(sampleContractors)
        }
    }
}