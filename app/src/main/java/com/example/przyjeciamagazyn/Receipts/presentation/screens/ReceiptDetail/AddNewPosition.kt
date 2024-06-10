package com.example.przyjeciamagazyn.Receipts.presentation.screens.ReceiptDetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.przyjeciamagazyn.Core.presentation.Screen
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptPosition
import com.example.przyjeciamagazyn.Receipts.presentation.ReceiptViewModel

@Composable
fun AddNewPosition(
    receiptViewModel: ReceiptViewModel,
    onNavigate: (String) -> Unit
) {
    val receiptId  = receiptViewModel.selectedDocument.collectAsState().value?.id ?: 0
    var productName by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PositionInputFields(
            productName = productName,
            onProductNameChange = { productName = it },
            unit = unit,
            onUnitChange = { unit = it },
            quantity = quantity,
            onQuantityChange = { quantity = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        AddPositionButton(
            productName = productName,
            unit = unit,
            quantity = quantity,
            receiptId = receiptId,
            receiptViewModel = receiptViewModel,
        ) { route -> onNavigate(route) }
    }
}

@Composable
fun PositionInputFields(
    productName: String,
    onProductNameChange: (String) -> Unit,
    unit: String,
    onUnitChange: (String) -> Unit,
    quantity: String,
    onQuantityChange: (String) -> Unit
) {
    TextField(
        value = productName,
        onValueChange = onProductNameChange,
        label = { Text("Product Name") },
        modifier = Modifier.fillMaxWidth()
    )
    TextField(
        value = unit,
        onValueChange = onUnitChange,
        label = { Text("Unit") },
        modifier = Modifier.fillMaxWidth()
    )
    TextField(
        value = quantity,
        onValueChange = onQuantityChange,
        label = { Text("Quantity") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun AddPositionButton(
    productName: String,
    unit: String,
    quantity: String,
    receiptId: Int,
    receiptViewModel: ReceiptViewModel,
    onNavigate: (String) -> Unit
) {
    val isQuantityValid = quantity.toIntOrNull() != null

    Button(
        onClick = {
            if (productName.isNotEmpty() && unit.isNotEmpty() && isQuantityValid) {
                val newPosition = ReceiptPosition(
                    receiptId = receiptId,
                    productName = productName,
                    unit = unit,
                    quantity = quantity.toInt()
                )
                receiptViewModel.insertReceiptPosition(newPosition)
                onNavigate(Screen.DocumentDetailScreen.route)
            }
        }
    ) {
        Text("Add Position")
    }
}