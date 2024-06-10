package com.example.przyjeciamagazyn.Core.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorListScreen
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Core.data.sampleContractors
import com.example.przyjeciamagazyn.Core.data.sampleDocuments
import com.example.przyjeciamagazyn.Receipts.presentation.screens.ReceiptListScreen
import com.example.przyjeciamagazyn.Receipts.presentation.screens.DocumentPositionDetailScreen
import com.example.przyjeciamagazyn.Receipts.presentation.screens.DocumentDetailScreen
import com.example.przyjeciamagazyn.Home.HomeScreen
import com.example.przyjeciamagazyn.Receipts.presentation.ReceiptViewModel
import com.example.przyjeciamagazyn.Receipts.presentation.screens.AddNewReceipt

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
    data object ReceiptDocumentScreen : Screen("document_list_screen")
    data object DocumentDetailScreen : Screen("document_detail_screen")
    data object DocumentPositionDetailScreen : Screen("document_position_detail_screen")
    data object ContractorListScreen : Screen("contractor_list_screen")
}

sealed class AddScreens(val route: String) {
    data object AddDocumentSheet : AddScreens("add_document_sheet")
    data object AddPositionSheet : AddScreens("add_position_sheet")
}

@Composable
fun NavigationNavGraph(navController: NavHostController) {
    val receiptViewModel = hiltViewModel<ReceiptViewModel>()
    val contractorViewModel = hiltViewModel<ContractorViewModel>()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(){ route -> navController.navigate(route) }
        }
        composable(route = Screen.ReceiptDocumentScreen.route) {
            ReceiptListScreen(receiptViewModel) { route -> navController.navigate(route) }
        }

        composable(route = AddScreens.AddDocumentSheet.route) {
            AddNewReceipt(contractorViewModel, receiptViewModel) { route -> navController.navigate(route) }
        }

        composable(route = Screen.DocumentDetailScreen.route) {
            DocumentDetailScreen(receiptViewModel) { route -> navController.navigate(route) }
        }
        composable(route = Screen.DocumentPositionDetailScreen.route) {
            DocumentPositionDetailScreen(receiptViewModel)
        }
        composable(route = Screen.ContractorListScreen.route) {
            ContractorListScreen(contractorViewModel)
        }
    }
}