package com.example.przyjeciamagazyn.Receipts.presentation.screens.Receipt

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.Core.presentation.AddScreens
import com.example.przyjeciamagazyn.Core.presentation.BackButton
import com.example.przyjeciamagazyn.Core.presentation.Screen
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptDocument
import com.example.przyjeciamagazyn.Receipts.presentation.ReceiptViewModel

@Composable
fun ReceiptListScreen(receiptViewModel: ReceiptViewModel, onNavigate: (String) -> Unit, ) {
    val documents = receiptViewModel.receiptDocuments.collectAsState(emptyList()).value
    val selectedDocument = receiptViewModel.selectedDocument

    LaunchedEffect(key1 = documents) {
        receiptViewModel.getALlReceipts()
    }

    Column(Modifier.fillMaxSize()) {
        BackButton("Receipt List",Modifier.padding(top = 20.dp, start = 15.dp)) {route -> onNavigate(route)}
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(documents) { ReceiptDocument ->
                DocumentRow(ReceiptDocument) { document ->
                    selectedDocument.value = document
                    onNavigate(Screen.DocumentDetailScreen.route)
                }
            }
        }
        Button(
            onClick = { onNavigate(AddScreens.AddDocumentSheet.route) },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(16.dp))
        {
            Text(text = "Dodaj dokument")
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
