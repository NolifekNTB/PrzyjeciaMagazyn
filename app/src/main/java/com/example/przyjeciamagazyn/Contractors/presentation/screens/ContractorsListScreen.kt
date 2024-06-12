package com.example.przyjeciamagazyn.Contractors.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
    val contractorList = contractorViewModel.contractors.collectAsState(emptyList()).value

    Scaffold(
        topBar = {
            TopAppBarBack(screenTitle = "Contractors list") { route -> onNavigate(route) }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigate(AddScreens.AddContractorScreen.route) },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Contractor")
            }
        },
        content = { padding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(padding)) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(contractorList) { contractor ->
                        ContractorRow(contractor, contractorViewModel) { route -> onNavigate(route) }
                    }
                }
            }
        }
    )
}

@Composable
fun ContractorRow(contractor: Contractor, contractorViewModel: ContractorViewModel, onNavigate: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
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