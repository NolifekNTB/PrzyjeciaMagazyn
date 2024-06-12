package com.example.przyjeciamagazyn.Receipts.presentation.screens.ReceiptDetail


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Screen
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptPosition
import com.example.przyjeciamagazyn.Receipts.presentation.ReceiptViewModel
import androidx.compose.material3.Scaffold
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.Core.presentation.topAppBarBack


@Composable
fun EditPositionScreen(
    receiptViewModel: ReceiptViewModel,
    onNavigate: (String) -> Unit
) {
    val position = receiptViewModel.selectedDocumentPosition.collectAsState().value ?: return

    var productName by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    LaunchedEffect(position) {
        position.let {
            productName = it.productName
            unit = it.unit
            quantity = it.quantity.toString()
        }
    }

    Scaffold(
        topBar = {
            topAppBarBack("Edit Receipt Position") { route -> onNavigate(route) }
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
                PositionInputFields(
                    productName = productName,
                    onProductNameChange = { productName = it },
                    unit = unit,
                    onUnitChange = { unit = it },
                    quantity = quantity,
                    onQuantityChange = { quantity = it },
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (productName.isNotEmpty() && unit.isNotEmpty()) {
                            val updatedPosition = ReceiptPosition(
                                id = position.id,
                                receiptId = position.receiptId,
                                productName = productName,
                                unit = unit,
                                quantity = quantity.toInt()
                            )
                            Log.d("testowanie", "Position updated: $updatedPosition")
                            receiptViewModel.updateReceiptPosition(updatedPosition)
                            onNavigate(Screen.DocumentPositionDetailScreen.route)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text("Update Position", fontSize = 18.sp)
                }
            }
        }
    )
}
