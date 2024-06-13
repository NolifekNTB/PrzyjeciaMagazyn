package com.example.przyjeciamagazyn.Core.presentation.Navigation.Graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Core.presentation.Navigation.AddScreens
import com.example.przyjeciamagazyn.Core.presentation.Navigation.EditScreens
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Screen
import com.example.przyjeciamagazyn.Documents.presentation.DocumentViewModel
import com.example.przyjeciamagazyn.Documents.presentation.screens.DocumentPositions.PositionDetailScreen
import com.example.przyjeciamagazyn.Documents.presentation.screens.Document.AddDocumentScreen
import com.example.przyjeciamagazyn.Documents.presentation.screens.Document.EditDocumentScreen
import com.example.przyjeciamagazyn.Documents.presentation.screens.Document.DocumentListScreen
import com.example.przyjeciamagazyn.Documents.presentation.screens.DocumentPositions.AddPositionScreen
import com.example.przyjeciamagazyn.Documents.presentation.screens.DocumentPositions.PositionsListScreen
import com.example.przyjeciamagazyn.Documents.presentation.screens.DocumentPositions.EditPositionScreen

fun NavGraphBuilder.documentGraph(
    navController: NavHostController,
    contractorViewModel: ContractorViewModel,
    documentViewModel: DocumentViewModel
) {
    composable(route = Screen.DocumentListScreen.route) {
        DocumentListScreen(documentViewModel) { route ->
            handleNavigation(navController, route)
        }
    }
    composable(route = AddScreens.AddDocumentScreen.route) {
        AddDocumentScreen(contractorViewModel, documentViewModel) { route ->
            handleNavigation(navController, route)
        }
    }
    composable(route = EditScreens.EditDocumentScreen.route) {
        EditDocumentScreen(contractorViewModel, documentViewModel) { route ->
            handleNavigation(navController, route)
        }
    }
    composable(route = Screen.PositionsListScreen.route) {
        PositionsListScreen(documentViewModel) { route ->
            handleNavigation(navController, route)
        }
    }
    composable(route = AddScreens.AddPositionScreen.route) {
        AddPositionScreen(documentViewModel = documentViewModel) { route ->
            handleNavigation(navController, route)
        }
    }
    composable(route = EditScreens.EditPositionScreen.route) {
        EditPositionScreen(documentViewModel) { route ->
            handleNavigation(navController, route)
        }
    }
    composable(route = Screen.PositionDetailScreen.route) {
        PositionDetailScreen(documentViewModel) { route ->
            handleNavigation(navController, route)
        }
    }
}

private fun handleNavigation(navController: NavHostController, route: String) {
    if (route == "back") navController.popBackStack()
    else navController.navigate(route)
}
