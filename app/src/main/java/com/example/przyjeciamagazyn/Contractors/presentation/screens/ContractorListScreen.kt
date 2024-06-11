package com.example.przyjeciamagazyn.Contractors.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Core.presentation.Navigation.AddScreens
import com.example.przyjeciamagazyn.Core.presentation.BackButton
import com.example.przyjeciamagazyn.Core.presentation.Navigation.EditScreens

@Composable
fun ContractorListScreen(contractorViewModel: ContractorViewModel, onNavigate: (String) -> Unit) {
    val contractorList = contractorViewModel.contractors.collectAsState(emptyList()).value

    Column(modifier = Modifier.fillMaxSize()) {
        BackButton("Contractors list", Modifier.padding(top = 20.dp, start = 15.dp)) {route -> onNavigate(route)}
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(contractorList) { contractor ->
                ContractorRow(contractor, contractorViewModel) {route -> onNavigate(route)}
            }
        }
        Button(
            onClick = { onNavigate(AddScreens.AddContractorSheet.route) },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(16.dp))
        {
            Text(text = "Dodaj nowego kontrahenta")
        }
    }
}

@Composable
fun ContractorRow(contractor: Contractor, contractorViewModel: ContractorViewModel, onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                contractorViewModel.selectContractor(contractor)
                onNavigate(EditScreens.EditContractorSheet.route)
            }
    ) {
        Text(text = "Symbol: ${contractor.symbol}", fontSize = 18.sp)
        Text(text = "Nazwa: ${contractor.name}", fontSize = 18.sp)
    }
}