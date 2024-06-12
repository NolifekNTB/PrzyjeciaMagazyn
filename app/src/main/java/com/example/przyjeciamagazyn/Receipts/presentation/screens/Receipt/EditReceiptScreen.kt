package com.example.przyjeciamagazyn.Receipts.presentation.screens.Receipt

import androidx.compose.foundation.layout.Column
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
import com.example.przyjeciamagazyn.Core.presentation.BackButton
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Screen
import com.example.przyjeciamagazyn.Core.presentation.topAppBarBack
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptDocument
import com.example.przyjeciamagazyn.Receipts.presentation.ReceiptViewModel

@Composable
fun EditReceiptDocumentScreen(
    contractorViewModel: ContractorViewModel,
    receiptViewModel: ReceiptViewModel,
    onNavigate: (String) -> Unit
) {
    val receipt = receiptViewModel.selectedDocument.collectAsState().value
    val contractors by contractorViewModel.contractors.collectAsState(emptyList())

    var date by remember { mutableStateOf("") }
    var symbol by remember { mutableStateOf("") }
    val receiptId = receipt?.id ?: 0
    var selectedContractors by remember { mutableStateOf<List<Contractor>>(emptyList()) }
    var contractorListExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(receipt) {
        receipt?.let {
            date = it.date
            symbol = it.symbol
            selectedContractors = it.contractors
        }
    }

    Scaffold(
        topBar = {
            topAppBarBack("Edit Receipt") { route -> onNavigate(route) }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                DocumentInputFields(
                    date = date,
                    onDateChange = { date = it },
                    symbol = symbol,
                    onSymbolChange = { symbol = it }
                )
                Spacer(modifier = Modifier.height(16.dp))
                ContractorsDropdown(
                    contractors = contractors,
                    selectedContractors = selectedContractors,
                    onContractorsSelected = { selectedContractors = it },
                    expanded = contractorListExpanded,
                    onExpandedChange = { contractorListExpanded = it }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (date.isNotEmpty() && symbol.isNotEmpty() && selectedContractors.isNotEmpty()) {
                            val updatedReceipt = ReceiptDocument(
                                id = receiptId,
                                date = date,
                                symbol = symbol,
                                contractors = selectedContractors,
                                positions = receipt?.positions ?: emptyList()
                            )
                            receiptViewModel.updateReceipt(updatedReceipt)
                            onNavigate(Screen.ReceiptDocumentScreen.route)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Update Receipt", fontSize = 18.sp)
                }
            }
        }
    )
}