package com.example.przyjeciamagazyn.Core.presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Graphs.contractorGraph
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Graphs.documentGraph
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Graphs.homeGraph
import com.example.przyjeciamagazyn.Receipts.presentation.ReceiptViewModel

@Composable
fun NavigationNavGraph(navController: NavHostController) {
    val receiptViewModel = hiltViewModel<ReceiptViewModel>()
    val contractorViewModel = hiltViewModel<ContractorViewModel>()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        homeGraph(navController)
        contractorGraph(navController, contractorViewModel)
        documentGraph(navController, contractorViewModel, receiptViewModel)
    }
}