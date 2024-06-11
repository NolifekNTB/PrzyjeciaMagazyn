package com.example.przyjeciamagazyn.Core.presentation.Navigation.Graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Contractors.presentation.screens.AddNewContractor
import com.example.przyjeciamagazyn.Contractors.presentation.screens.ContractorListScreen
import com.example.przyjeciamagazyn.Contractors.presentation.screens.EditContractorScreen
import com.example.przyjeciamagazyn.Core.presentation.Navigation.AddScreens
import com.example.przyjeciamagazyn.Core.presentation.Navigation.EditScreens
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Screen

fun NavGraphBuilder.contractorGraph(
    navController: NavHostController,
    contractorViewModel: ContractorViewModel
) {
    composable(route = Screen.ContractorListScreen.route) {
        ContractorListScreen(contractorViewModel) { route ->
            if (route == "back") navController.popBackStack()
            else navController.navigate(route)
        }
    }
    composable(route = AddScreens.AddContractorSheet.route) {
        AddNewContractor(contractorViewModel = contractorViewModel) { route ->
            if (route == "back") navController.popBackStack()
            else navController.navigate(route)
        }
    }
    composable(route = EditScreens.EditContractorSheet.route) {
        EditContractorScreen(contractorViewModel) { route ->
            if (route == "back") navController.popBackStack()
            else navController.navigate(route)
        }
    }
}