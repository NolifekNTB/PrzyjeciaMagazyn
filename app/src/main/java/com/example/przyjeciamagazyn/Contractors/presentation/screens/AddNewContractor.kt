package com.example.przyjeciamagazyn.Contractors.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Core.presentation.BackButton
import com.example.przyjeciamagazyn.Core.presentation.Screen

@Composable
fun AddNewContractor(
    contractorViewModel: ContractorViewModel,
    onNavigate: (String) -> Unit
) {
    var symbol by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BackButton("Add New Contractor",Modifier.padding(5.dp)) {route -> onNavigate(route)}
        Spacer(modifier = Modifier.height(16.dp))
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

@Composable
fun ContractorInputFields(
    symbol: String,
    onSymbolChange: (String) -> Unit,
    name: String,
    onNameChange: (String) -> Unit
) {
    TextField(
        value = symbol,
        onValueChange = onSymbolChange,
        label = { Text("Symbol") },
        modifier = Modifier.fillMaxWidth()
    )
    TextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Name") },
        modifier = Modifier.fillMaxWidth()
    )
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
                onNavigate(Screen.ContractorListScreen.route)
            }
        }
    ) {
        Text("Add Contractor")
    }
}