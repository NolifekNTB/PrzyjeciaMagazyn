package com.example.przyjeciamagazyn.Receipts.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.Core.presentation.Screen
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptDocument
import com.example.przyjeciamagazyn.Receipts.presentation.ReceiptViewModel

@Composable
fun ReceiptListScreen(receiptViewModel: ReceiptViewModel, onNavigate: (String) -> Unit, ) {
    val documents = receiptViewModel.receiptDocuments.collectAsState(emptyList()).value
    val selectedDocument = receiptViewModel.selectedDocument

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(documents) { ReceiptDocument ->
            DocumentRow(ReceiptDocument) { document ->
                selectedDocument.value = document
                onNavigate(Screen.DocumentDetailScreen.route)
            }
        }
    }
}

@Composable
fun DocumentRow(ReceiptDocument: ReceiptDocument, whichDocument: (ReceiptDocument) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { whichDocument(ReceiptDocument) }
    ) {
        Text(text = "Data: ${ReceiptDocument.date}", fontSize = 18.sp)
        Text(text = "Symbol: ${ReceiptDocument.symbol}", fontSize = 18.sp)
        Text(text = "Kontrahent: ${ReceiptDocument.contractors}", fontSize = 18.sp)
    }
}
