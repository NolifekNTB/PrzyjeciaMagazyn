package com.example.przyjeciamagazyn.Receipts.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.Core.data.sampleDocuments
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptPosition
import com.example.przyjeciamagazyn.Receipts.presentation.ReceiptViewModel

@Composable
fun DocumentPositionDetailScreen(receiptViewModel: ReceiptViewModel) {
    val position = receiptViewModel.selectedDocumentPosition.collectAsState().value ?: return

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Nazwa towaru: ${position.productName}", fontSize = 18.sp)
        Text(text = "Jednostka miary: ${position.unit}", fontSize = 18.sp)
        Text(text = "Ilość: ${position.quantity}", fontSize = 18.sp)
    }
}