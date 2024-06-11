package com.example.przyjeciamagazyn.Core.presentation

import android.accessibilityservice.AccessibilityService.ScreenshotResult
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.przyjeciamagazyn.Contractors.presentation.AddNewContractor
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorListScreen
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Receipts.presentation.screens.Receipt.ReceiptListScreen
import com.example.przyjeciamagazyn.Receipts.presentation.screens.DocumentPositionDetailScreen
import com.example.przyjeciamagazyn.Receipts.presentation.screens.ReceiptDetail.DocumentDetailScreen
import com.example.przyjeciamagazyn.Home.HomeScreen
import com.example.przyjeciamagazyn.Receipts.presentation.ReceiptViewModel
import com.example.przyjeciamagazyn.Receipts.presentation.screens.Receipt.AddNewReceipt
import com.example.przyjeciamagazyn.Receipts.presentation.screens.Receipt.EditReceiptDocumentScreen
import com.example.przyjeciamagazyn.Receipts.presentation.screens.ReceiptDetail.AddNewPosition
import com.example.przyjeciamagazyn.Receipts.presentation.screens.ReceiptDetail.EditPositionScreen

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
    data object AddContractorSheet : AddScreens("add_contractor_sheet")
}

sealed class EditScreens(val route: String) {
    data object EditDocumentSheet : EditScreens("edit_document_sheet")
    data object EditPositionSheet : EditScreens("edit_position_sheet")
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

        ////////////////////////////////////////////////////////////////////////////////

        composable(route = Screen.ReceiptDocumentScreen.route) {
            ReceiptListScreen(receiptViewModel) { route -> navController.navigate(route) }
        }

        composable(route = AddScreens.AddDocumentSheet.route) {
            AddNewReceipt(contractorViewModel, receiptViewModel) { route -> navController.navigate(route) }
        }

        composable(route = EditScreens.EditDocumentSheet.route) {
            EditReceiptDocumentScreen(contractorViewModel, receiptViewModel) { route -> navController.navigate(route) }
        }

        ////////////////////////////////////////////////////////////////////////////////

        composable(route = Screen.DocumentDetailScreen.route) {
            DocumentDetailScreen(receiptViewModel) { route -> navController.navigate(route) }
        }

        composable(route = AddScreens.AddPositionSheet.route) {
            AddNewPosition(receiptViewModel = receiptViewModel) { route -> navController.navigate(route) }
        }

        composable(route = Screen.DocumentPositionDetailScreen.route) {
            DocumentPositionDetailScreen(receiptViewModel) { route -> navController.navigate(route) }
        }

        composable(route = EditScreens.EditPositionSheet.route) {
            EditPositionScreen(receiptViewModel) { route -> navController.navigate(route) }
        }

        ////////////////////////////////////////////////////////////////////////////////

        composable(route = Screen.ContractorListScreen.route) {
            ContractorListScreen(contractorViewModel) { route -> navController.navigate(route) }
        }

        composable(route = AddScreens.AddContractorSheet.route) {
            AddNewContractor(contractorViewModel = contractorViewModel) { route -> navController.navigate(route) }
        }

    }
}