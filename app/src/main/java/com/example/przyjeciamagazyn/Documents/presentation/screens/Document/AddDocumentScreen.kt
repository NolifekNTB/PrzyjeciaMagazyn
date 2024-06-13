package com.example.przyjeciamagazyn.Documents.presentation.screens.Document

import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Core.presentation.Shared.TopAppBarBack
import com.example.przyjeciamagazyn.R
import com.example.przyjeciamagazyn.Documents.data.model.Document
import com.example.przyjeciamagazyn.Documents.presentation.DocumentViewModel

@Composable
fun AddDocumentScreen(
    contractorViewModel: ContractorViewModel,
    receiptViewModel: DocumentViewModel,
    onNavigate: (String) -> Unit
) {
    var date by remember { mutableStateOf("") }
    var symbol by remember { mutableStateOf("") }
    var selectedContractors by remember { mutableStateOf<List<Contractor>>(emptyList()) }
    var contractorListExpanded by remember { mutableStateOf(false) }
    val contractors = contractorViewModel.contractors.collectAsState(emptyList()).value

    Scaffold(
        topBar = { AddDocumentTopBar(onNavigate) },
        content = { paddingValues ->
            AddDocumentContent(
                date = date,
                onDateChange = { date = it },
                symbol = symbol,
                onSymbolChange = { symbol = it },
                contractors = contractors,
                selectedContractors = selectedContractors,
                onContractorsSelected = { selectedContractors = it },
                contractorListExpanded = contractorListExpanded,
                onExpandedChange = { contractorListExpanded = it },
                onAddDocumentClick = {
                    if (date.isNotEmpty() && symbol.isNotEmpty() && selectedContractors.isNotEmpty()) {
                        val newDocument = Document(
                            date = date,
                            symbol = symbol,
                            contractors = selectedContractors,
                            positions = listOf()
                        )
                        receiptViewModel.insertDocument(newDocument)
                        onNavigate("back")
                    }
                },
                paddingValues = paddingValues
            )
        }
    )
}

@Composable
fun AddDocumentTopBar(onNavigate: (String) -> Unit) {
    TopAppBarBack("Add New Receipt") { route -> onNavigate(route) }
}

@Composable
fun AddDocumentContent(
    date: String,
    onDateChange: (String) -> Unit,
    symbol: String,
    onSymbolChange: (String) -> Unit,
    contractors: List<Contractor>,
    selectedContractors: List<Contractor>,
    onContractorsSelected: (List<Contractor>) -> Unit,
    contractorListExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onAddDocumentClick: () -> Unit,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DocumentInputFields(
            date = date,
            onDateChange = onDateChange,
            symbol = symbol,
            onSymbolChange = onSymbolChange
        )
        Spacer(modifier = Modifier.height(16.dp))
        ContractorsDropdown(
            contractors = contractors,
            selectedContractors = selectedContractors,
            onContractorsSelected = onContractorsSelected,
            expanded = contractorListExpanded,
            onExpandedChange = onExpandedChange
        )
        Spacer(modifier = Modifier.height(24.dp))
        AddDocumentButton(onClick = onAddDocumentClick)
    }
}


@Composable
fun DocumentInputFields(
    date: String,
    onDateChange: (String) -> Unit,
    symbol: String,
    onSymbolChange: (String) -> Unit
) {
    Column {
        TextField(
            value = date,
            onValueChange = onDateChange,
            label = { Text("Date") },
            placeholder = { Text("Enter date") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(Icons.Default.DateRange, contentDescription = "Date")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = symbol,
            onValueChange = onSymbolChange,
            label = { Text("Symbol") },
            placeholder = { Text("Enter symbol") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(painterResource(id = R.drawable.ic_label), contentDescription = "Symbol")
            }
        )
    }
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
fun AddDocumentButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Add Document")
    }
}