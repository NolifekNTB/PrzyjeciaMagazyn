package com.example.przyjeciamagazyn.Contractors.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Core.presentation.Shared.TopAppBarBack

@Composable
fun AddContractorScreen(
    contractorViewModel: ContractorViewModel,
    onNavigate: (String) -> Unit
) {
    var symbol by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AddContractorTopAppBar(onNavigate) },
        content = { padding ->
            AddContractorForm(
                symbol = symbol,
                onSymbolChange = { symbol = it },
                name = name,
                onNameChange = { name = it },
                contractorViewModel = contractorViewModel,
                onNavigate = onNavigate,
                padding = padding
            )
        }
    )
}

@Composable
fun AddContractorTopAppBar(onNavigate: (String) -> Unit) {
    TopAppBarBack(screenTitle = "Add New Contractor") { route -> onNavigate(route) }
}

@Composable
fun AddContractorForm(
    symbol: String,
    onSymbolChange: (String) -> Unit,
    name: String,
    onNameChange: (String) -> Unit,
    contractorViewModel: ContractorViewModel,
    onNavigate: (String) -> Unit,
    padding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ContractorInputFields(
            symbol = symbol,
            onSymbolChange = onSymbolChange,
            name = name,
            onNameChange = onNameChange
        )
        Spacer(modifier = Modifier.height(16.dp))
        SubmitContractorButton(
            symbol = symbol,
            name = name,
            contractorViewModel = contractorViewModel
        ) { route -> onNavigate(route) }
    }
}

@Composable
fun ContractorInputFields(
    symbol: String,
    onSymbolChange: (String) -> Unit,
    name: String,
    onNameChange: (String) -> Unit
) {
    Column {
        TextField(
            value = symbol,
            onValueChange = onSymbolChange,
            label = { Text("Symbol") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun SubmitContractorButton(
    symbol: String,
    name: String,
    contractorViewModel: ContractorViewModel,
    onNavigate: (String) -> Unit
) {
    Button(
        onClick = {
            if (symbol.isNotEmpty() && name.isNotEmpty()) {
                val newContractor = Contractor(
                    symbol = symbol,
                    name = name
                )
                contractorViewModel.insertContractor(newContractor)
                onNavigate("back")
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("Add Contractor")
    }
}