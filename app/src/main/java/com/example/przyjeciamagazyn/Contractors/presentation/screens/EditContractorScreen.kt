package com.example.przyjeciamagazyn.Contractors.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Core.presentation.BackButton
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Screen

@Composable
fun EditContractorScreen(
    contractorViewModel: ContractorViewModel,
    onNavigate: (String) -> Unit
) {
    val contractor = contractorViewModel.selectedContractor.collectAsState(null).value

    var symbol by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    LaunchedEffect(contractor) {
        contractor?.let {
            symbol = it.symbol
            name = it.name
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BackButton("Edit Contractor", Modifier.padding(10.dp)) {route -> onNavigate(route)}
        Spacer(modifier = Modifier.height(10.dp))
        ContractorInputFields(
            symbol = symbol,
            onSymbolChange = { symbol = it },
            name = name,
            onNameChange = { name = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (symbol.isNotEmpty() && name.isNotEmpty() && contractor != null) {
                    val updatedContractor = Contractor(
                        id = contractor.id,
                        symbol = symbol,
                        name = name
                    )
                    contractorViewModel.updateContractor(updatedContractor)
                    onNavigate(Screen.ContractorListScreen.route)
                }
            }
        ) {
            Text("Update Contractor")
        }
    }
}