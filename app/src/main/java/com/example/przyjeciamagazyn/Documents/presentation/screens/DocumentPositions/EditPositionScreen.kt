package com.example.przyjeciamagazyn.Documents.presentation.screens.DocumentPositions


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import com.example.przyjeciamagazyn.Documents.data.model.DocumentPosition
import com.example.przyjeciamagazyn.Documents.presentation.DocumentViewModel
import androidx.compose.material3.Scaffold
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.Core.presentation.Shared.TopAppBarBack


@Composable
fun EditPositionScreen(
    receiptViewModel: DocumentViewModel,
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
            TopAppBarBack("Edit Receipt Position") { route -> onNavigate(route) }
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
                            val updatedPosition = DocumentPosition(
                                id = position.id,
                                receiptId = position.receiptId,
                                productName = productName,
                                unit = unit,
                                quantity = quantity.toInt()
                            )
                            receiptViewModel.updateReceiptPosition(updatedPosition)
                            onNavigate("back")
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
