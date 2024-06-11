package com.example.przyjeciamagazyn.Receipts.presentation.screens.Receipt

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Core.presentation.BackButton
import com.example.przyjeciamagazyn.Core.presentation.Screen
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptDocument
import com.example.przyjeciamagazyn.Receipts.presentation.ReceiptViewModel

@Composable
fun AddNewReceipt(
    contractorViewModel: ContractorViewModel,
    receiptViewModel: ReceiptViewModel,
    onNavigate: (String) -> Unit
) {
    var date by remember { mutableStateOf("") }
    var symbol by remember { mutableStateOf("") }
    var selectedContractors by remember { mutableStateOf<List<Contractor>>(emptyList()) }
    var contractorListExpanded by remember { mutableStateOf(false) }
    val contractors = contractorViewModel.contractors.collectAsState(emptyList()).value

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BackButton("Add New Receipt",Modifier.padding(top = 10.dp)) {route -> onNavigate(route)}
        Spacer(modifier = Modifier.height(15.dp))
        DocumentInputFields(
            date = date,
            onDateChange = { date = it },
            symbol = symbol,
            onSymbolChange = { symbol = it }
        )
        ContractorsDropdown(
            contractors = contractors,
            selectedContractors = selectedContractors,
            onContractorsSelected = { selectedContractors = it },
            expanded = contractorListExpanded,
            onExpandedChange = { contractorListExpanded = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        AddDocumentButton(
            date = date,
            symbol = symbol,
            selectedContractors = selectedContractors,
            receiptViewModel = receiptViewModel,
        ) {route -> onNavigate(route)}
    }
}

@Composable
fun DocumentInputFields(
    date: String,
    onDateChange: (String) -> Unit,
    symbol: String,
    onSymbolChange: (String) -> Unit
) {
    TextField(
        value = date,
        onValueChange = onDateChange,
        label = { Text("Date") },
        modifier = Modifier.fillMaxWidth()
    )
    TextField(
        value = symbol,
        onValueChange = onSymbolChange,
        label = { Text("Symbol") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ContractorsDropdown(
    contractors: List<Contractor>,
    selectedContractors: List<Contractor>,
    onContractorsSelected: (List<Contractor>) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = selectedContractors.joinToString { it.name },
            onValueChange = {},
            label = { Text("Contractors") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { onExpandedChange(true) }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Select Contractors")
                }
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) }
        ) {
            contractors.forEach { contractor ->
                DropdownMenuItem(onClick = {
                    onContractorsSelected(
                        if (selectedContractors.contains(contractor)) {
                            selectedContractors - contractor
                        } else {
                            selectedContractors + contractor
                        }
                    )
                }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = selectedContractors.contains(contractor),
                            onCheckedChange = null
                        )
                        Text(contractor.name, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun AddDocumentButton(
    date: String,
    symbol: String,
    selectedContractors: List<Contractor>,
    receiptViewModel: ReceiptViewModel,
    onNavigate: (String) -> Unit
) {
    Button(
        onClick = {
            if (date.isNotEmpty() && symbol.isNotEmpty() && selectedContractors.isNotEmpty()) {
                val newDocument = ReceiptDocument(
                    date = date,
                    symbol = symbol,
                    contractors = selectedContractors,
                    positions = listOf()
                )
                receiptViewModel.insertReceipt(newDocument)
                onNavigate(Screen.ReceiptDocumentScreen.route)
            }
        }
    ) {
        Text("Add Document")
    }
}