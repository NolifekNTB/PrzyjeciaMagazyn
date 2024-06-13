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
    val contractor by contractorViewModel.selectedContractor.collectAsState(null)

    var symbol by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    LaunchedEffect(contractor) {
        contractor?.let {
            symbol = it.symbol
            name = it.name
        }
    }

    Scaffold(
        topBar = { EditContractorTopBar(onNavigate) },
        content = { padding ->
            EditContractorContent(
                symbol = symbol,
                onSymbolChange = { symbol = it },
                name = name,
                onNameChange = { name = it },
                contractor = contractor,
                onUpdateClick = {
                    if (symbol.isNotEmpty() && name.isNotEmpty() && contractor != null) {
                        val updatedContractor = Contractor(
                            id = contractor!!.id,
                            symbol = symbol,
                            name = name
                        )
                        contractorViewModel.updateContractor(updatedContractor)
                        onNavigate("back")
                    }
                },
                padding = padding
            )
        }
    )
}

@Composable
fun EditContractorTopBar(onNavigate: (String) -> Unit) {
    TopAppBarBack(screenTitle = "Edit Contractor") { route -> onNavigate(route) }
}

@Composable
fun EditContractorContent(
    symbol: String,
    onSymbolChange: (String) -> Unit,
    name: String,
    onNameChange: (String) -> Unit,
    contractor: Contractor?,
    onUpdateClick: () -> Unit,
    padding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ContractorInputFields(
            symbol = symbol,
            onSymbolChange = onSymbolChange,
            name = name,
            onNameChange = onNameChange
        )
        Spacer(modifier = Modifier.height(16.dp))
        UpdateContractorButton(onUpdateClick)
    }
}

@Composable
fun UpdateContractorButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("Update Contractor")
    }
}