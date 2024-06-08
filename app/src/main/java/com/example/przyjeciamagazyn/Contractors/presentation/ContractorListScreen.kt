package com.example.przyjeciamagazyn.Contractors.presentation

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor

@Composable
fun ContractorListScreen() {
    val contractorViewModel = hiltViewModel<ContractorViewModel>()
    val contractorList = contractorViewModel.contractors.collectAsState(emptyList()).value

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(contractorList) { contractor ->
                ContractorRow(contractor)
            }
        }
        Button(
            onClick = {
                contractorViewModel.insertContractor(Contractor("NEW", "New Contractor"))
                contractorViewModel.getAllContractors()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Dodaj nowego kontrahenta", fontSize = 18.sp)
        }
    }
}

@Composable
fun ContractorRow(contractor: Contractor) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Symbol: ${contractor.symbol}", fontSize = 18.sp)
        Text(text = "Nazwa: ${contractor.name}", fontSize = 18.sp)
    }
}