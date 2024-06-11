package com.example.przyjeciamagazyn.Core.presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.przyjeciamagazyn.Contractors.presentation.screens.AddNewContractor
import com.example.przyjeciamagazyn.Contractors.presentation.screens.ContractorListScreen
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Contractors.presentation.screens.EditContractorScreen
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Graphs.contractorGraph
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Graphs.documentGraph
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Graphs.homeGraph
import com.example.przyjeciamagazyn.Receipts.presentation.screens.Receipt.ReceiptListScreen
import com.example.przyjeciamagazyn.Receipts.presentation.screens.DocumentPositionDetailScreen
import com.example.przyjeciamagazyn.Receipts.presentation.screens.ReceiptDetail.DocumentDetailScreen
import com.example.przyjeciamagazyn.Home.HomeScreen
import com.example.przyjeciamagazyn.Receipts.presentation.ReceiptViewModel
import com.example.przyjeciamagazyn.Receipts.presentation.screens.Receipt.AddNewReceipt
import com.example.przyjeciamagazyn.Receipts.presentation.screens.Receipt.EditReceiptDocumentScreen
import com.example.przyjeciamagazyn.Receipts.presentation.screens.ReceiptDetail.AddNewPosition
import com.example.przyjeciamagazyn.Receipts.presentation.screens.ReceiptDetail.EditPositionScreen

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