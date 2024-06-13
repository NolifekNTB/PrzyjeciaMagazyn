package com.example.przyjeciamagazyn.Documents.presentation.screens.Document

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Core.presentation.Shared.TopAppBarBack
import com.example.przyjeciamagazyn.Documents.data.model.Document
import com.example.przyjeciamagazyn.Documents.presentation.DocumentViewModel

@Composable
fun EditDocumentScreen(
    contractorViewModel: ContractorViewModel,
    documentViewModel: DocumentViewModel,
    onNavigate: (String) -> Unit
) {
    val document = documentViewModel.selectedDocument.collectAsState().value
    val contractors by contractorViewModel.contractors.collectAsState(emptyList())

    var date by remember { mutableStateOf("") }
    var symbol by remember { mutableStateOf("") }
    val documentId = document?.id ?: 0
    var selectedContractors by remember { mutableStateOf<List<Contractor>>(emptyList()) }
    var contractorListExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(document) {
        document?.let {
            date = it.date
            symbol = it.symbol
            selectedContractors = it.contractors
        }
    }

    Scaffold(
        topBar = { EditDocumentTopBar { onNavigate("back") } },
        content = { paddingValues ->
            EditDocumentContent(
                date = date,
                onDateChange = { date = it },
                symbol = symbol,
                onSymbolChange = { symbol = it },
                contractors = contractors,
                selectedContractors = selectedContractors,
                onContractorsSelected = { selectedContractors = it },
                contractorListExpanded = contractorListExpanded,
                onExpandedChange = { contractorListExpanded = it },
                onUpdateClick = {
                    if (date.isNotEmpty() && symbol.isNotEmpty()) {
                        val updateddocument = Document(
                            id = documentId,
                            date = date,
                            symbol = symbol,
                            contractors = selectedContractors,
                            positions = document?.positions ?: emptyList()
                        )
                        documentViewModel.updateDocument(updateddocument)
                        onNavigate("back")
                    }
                },
                paddingValues = paddingValues
            )
        }
    )
}

@Composable
fun EditDocumentTopBar(onNavigate: (String) -> Unit) {
    TopAppBarBack("Edit document") { route -> onNavigate(route) }
}

@Composable
fun EditDocumentContent(
    date: String,
    onDateChange: (String) -> Unit,
    symbol: String,
    onSymbolChange: (String) -> Unit,
    contractors: List<Contractor>,
    selectedContractors: List<Contractor>,
    onContractorsSelected: (List<Contractor>) -> Unit,
    contractorListExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onUpdateClick: () -> Unit,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(15.dp))
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
        Spacer(modifier = Modifier.height(16.dp))
        UpdateDocumentButton(onClick = onUpdateClick)
    }
}

@Composable
fun UpdateDocumentButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("Update Document", fontSize = 18.sp)
    }
}
