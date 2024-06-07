package com.example.przyjeciamagazyn.Receipts.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptPosition

@Composable
fun DocumentPositionDetailScreen(position: ReceiptPosition) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Nazwa towaru: ${position.productName}", fontSize = 18.sp)
        Text(text = "Jednostka miary: ${position.unit}", fontSize = 18.sp)
        Text(text = "Ilość: ${position.quantity}", fontSize = 18.sp)
    }
}