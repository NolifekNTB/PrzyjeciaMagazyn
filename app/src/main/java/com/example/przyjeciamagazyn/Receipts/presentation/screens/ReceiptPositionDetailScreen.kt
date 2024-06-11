package com.example.przyjeciamagazyn.Receipts.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.Core.presentation.EditScreens
import com.example.przyjeciamagazyn.Receipts.presentation.ReceiptViewModel

@Composable
fun DocumentPositionDetailScreen(receiptViewModel: ReceiptViewModel, onNavigate: (String) -> Unit) {
    val position = receiptViewModel.selectedDocumentPosition.collectAsState().value ?: return
    Log.d("testowanie", "Position after: $position")

    Column(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(text = "Nazwa towaru: ${position.productName}", fontSize = 18.sp)
            Text(text = "Jednostka miary: ${position.unit}", fontSize = 18.sp)
            Text(text = "Ilość: ${position.quantity}", fontSize = 18.sp)
        }
        Button(
            onClick = { onNavigate(EditScreens.EditPositionSheet.route) },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(16.dp))
        {
            Text(text = "Edytuj pozycję")
        }
    }
}