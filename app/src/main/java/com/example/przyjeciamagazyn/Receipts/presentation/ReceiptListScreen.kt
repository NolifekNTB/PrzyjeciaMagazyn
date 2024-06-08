package com.example.przyjeciamagazyn.Receipts.presentation

import android.util.Log
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.przyjeciamagazyn.Core.presentation.Screen
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptDocument

@Composable
fun ReceiptDocumentScreen(onNavigate: (String) -> Unit, ) {
    val viewModel = hiltViewModel<ReceiptViewModel>()
    val documents = viewModel.receiptDocuments.collectAsState(emptyList()).value

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(documents) { ReceiptDocument ->
            DocumentRow(ReceiptDocument) { route -> onNavigate(route) }
        }
    }
}

@Composable
fun DocumentRow(ReceiptDocument: ReceiptDocument, onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onNavigate(Screen.DocumentDetailScreen.route) }
    ) {
        Text(text = "Data: ${ReceiptDocument.date}", fontSize = 18.sp)
        Text(text = "Symbol: ${ReceiptDocument.symbol}", fontSize = 18.sp)
        Text(text = "Kontrahent: ${ReceiptDocument.contractor}", fontSize = 18.sp)
    }
}
