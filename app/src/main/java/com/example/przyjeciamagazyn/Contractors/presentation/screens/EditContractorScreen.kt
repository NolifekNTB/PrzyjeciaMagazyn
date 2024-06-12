package com.example.przyjeciamagazyn.Contractors.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Core.presentation.Shared.TopAppBarBack

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

    Scaffold(
        topBar = {
            TopAppBarBack(screenTitle = "Edit Contractor") { route -> onNavigate(route) }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
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
                            onNavigate("back")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Update Contractor")
                }
            }
        }
    )
}
