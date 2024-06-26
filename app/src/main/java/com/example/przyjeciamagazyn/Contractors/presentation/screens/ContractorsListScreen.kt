package com.example.przyjeciamagazyn.Contractors.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Core.presentation.Navigation.AddScreens
import com.example.przyjeciamagazyn.Core.presentation.Navigation.EditScreens
import com.example.przyjeciamagazyn.Core.presentation.Shared.TopAppBarBack

@Composable
fun ContractorListScreen(contractorViewModel: ContractorViewModel, onNavigate: (String) -> Unit) {
    val contractorList by contractorViewModel.contractors.collectAsState(emptyList())

    Scaffold(
        topBar = { ContractorListTopAppBar(onNavigate) },
        floatingActionButton = { SubmitContractorButton(onNavigate) },
        content = { padding ->
            ContractorListView(contractorList, contractorViewModel, onNavigate, padding)
        }
    )
}

@Composable
fun ContractorListTopAppBar(onNavigate: (String) -> Unit) {
    TopAppBarBack(screenTitle = "Contractors list") { route -> onNavigate(route) }
}

@Composable
fun SubmitContractorButton(onNavigate: (String) -> Unit) {
    FloatingActionButton(
        onClick = { onNavigate(AddScreens.AddContractorScreen.route) },
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Contractor")
    }
}

@Composable
fun ContractorListView(
    contractorList: List<Contractor>,
    contractorViewModel: ContractorViewModel,
    onNavigate: (String) -> Unit,
    padding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(contractorList) { contractor ->
                ContractorListItem(contractor, contractorViewModel, onNavigate)
            }
        }
    }
}

@Composable
fun ContractorListItem(contractor: Contractor, contractorViewModel: ContractorViewModel, onNavigate: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                // Navigate to the contractor edit screen when the card is clicked
                contractorViewModel.selectContractor(contractor)
                onNavigate(EditScreens.EditContractorScreen.route)
            },
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Symbol: ${contractor.symbol}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = "Nazwa: ${contractor.name}", fontSize = 18.sp)
        }
    }
}