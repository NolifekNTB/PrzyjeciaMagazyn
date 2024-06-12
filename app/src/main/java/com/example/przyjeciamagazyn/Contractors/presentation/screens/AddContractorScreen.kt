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
import com.example.przyjeciamagazyn.Core.presentation.topAppBarBack

@Composable
fun AddNewContractor(
    contractorViewModel: ContractorViewModel,
    onNavigate: (String) -> Unit
) {
    var symbol by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            topAppBarBack(nameOfTheScreen = "Add New Contractor") { route -> onNavigate(route) }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ContractorInputFields(
                    symbol = symbol,
                    onSymbolChange = { symbol = it },
                    name = name,
                    onNameChange = { name = it }
                )
                Spacer(modifier = Modifier.height(16.dp))
                AddContractorButton(
                    symbol = symbol,
                    name = name,
                    contractorViewModel = contractorViewModel
                ) { route -> onNavigate(route) }
            }
        }
    )
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
fun AddContractorButton(
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