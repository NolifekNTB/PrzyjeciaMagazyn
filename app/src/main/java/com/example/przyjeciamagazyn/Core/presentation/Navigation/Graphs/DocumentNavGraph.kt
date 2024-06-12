package com.example.przyjeciamagazyn.Core.presentation.Navigation.Graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Core.presentation.Navigation.AddScreens
import com.example.przyjeciamagazyn.Core.presentation.Navigation.EditScreens
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Screen
import com.example.przyjeciamagazyn.Documents.presentation.DocumentViewModel
import com.example.przyjeciamagazyn.Documents.presentation.screens.DocumentPositions.DocumentPositionDetailScreen
import com.example.przyjeciamagazyn.Documents.presentation.screens.Document.AddNewReceipt
import com.example.przyjeciamagazyn.Documents.presentation.screens.Document.EditReceiptDocumentScreen
import com.example.przyjeciamagazyn.Documents.presentation.screens.Document.ReceiptListScreen
import com.example.przyjeciamagazyn.Documents.presentation.screens.DocumentPositions.AddNewPosition
import com.example.przyjeciamagazyn.Documents.presentation.screens.DocumentPositions.DocumentPositionsList
import com.example.przyjeciamagazyn.Documents.presentation.screens.DocumentPositions.EditPositionScreen

fun NavGraphBuilder.documentGraph(
    navController: NavHostController,
    contractorViewModel: ContractorViewModel,
    receiptViewModel: DocumentViewModel
) {
    composable(route = Screen.ReceiptDocumentScreen.route) {
        ReceiptListScreen(receiptViewModel) { route ->
            if (route == "back") navController.popBackStack()
            else navController.navigate(route)
        }
    }
    composable(route = AddScreens.AddDocumentSheet.route) {
        AddNewReceipt(contractorViewModel, receiptViewModel) { route ->
            if (route == "back") navController.popBackStack()
            else navController.navigate(route)
        }
    }
    composable(route = EditScreens.EditDocumentSheet.route) {
        EditReceiptDocumentScreen(contractorViewModel, receiptViewModel) { route ->
            if (route == "back") navController.popBackStack()
            else navController.navigate(route)
        }
    }
    composable(route = Screen.DocumentDetailScreen.route) {
        DocumentPositionsList(receiptViewModel) { route ->
            if (route == "back") navController.popBackStack()
            else navController.navigate(route)
        }
    }
    composable(route = AddScreens.AddPositionSheet.route) {
        AddNewPosition(receiptViewModel = receiptViewModel) { route ->
            if (route == "back") navController.popBackStack()
            else navController.navigate(route)
        }
    }
    composable(route = Screen.DocumentPositionDetailScreen.route) {
        DocumentPositionDetailScreen(receiptViewModel) { route ->
            if (route == "back") navController.popBackStack()
            else navController.navigate(route)
        }
    }
    composable(route = EditScreens.EditPositionSheet.route) {
        EditPositionScreen(receiptViewModel) { route ->
            if (route == "back") navController.popBackStack()
            else navController.navigate(route)
        }
    }
}