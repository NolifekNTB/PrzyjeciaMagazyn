package com.example.przyjeciamagazyn.Receipts.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptPosition
import com.example.przyjeciamagazyn.Core.presentation.Screen
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptDocument

@Composable
fun DocumentDetailScreen(receipt: ReceiptDocument, onNavigate: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(25.dp)) {
        Text(text = "Data: ${receipt.date}", fontSize = 18.sp)
        Text(text = "Symbol: ${receipt.symbol}", fontSize = 18.sp)
        Text(text = "Kontrahent: ${receipt.contractor}", fontSize = 18.sp)

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(receipt.positions) { position ->
                DocumentPositionRow(position) { route -> onNavigate(route) }
            }
        }
    }
}

@Composable
fun DocumentPositionRow(position: ReceiptPosition, onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onNavigate(Screen.DocumentPositionDetailScreen.route) }
    ) {
        Text(text = "Nazwa towaru: ${position.productName}", fontSize = 18.sp)
        Text(text = "Jednostka miary: ${position.unit}", fontSize = 18.sp)
        Text(text = "Ilość: ${position.quantity}", fontSize = 18.sp)
    }
}