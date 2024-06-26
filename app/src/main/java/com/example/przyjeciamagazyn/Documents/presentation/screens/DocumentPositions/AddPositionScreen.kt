package com.example.przyjeciamagazyn.Documents.presentation.screens.DocumentPositions

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.Core.presentation.Shared.TopAppBarBack
import com.example.przyjeciamagazyn.Documents.data.model.Position
import com.example.przyjeciamagazyn.Documents.presentation.DocumentViewModel

@Composable
fun AddPositionScreen(
    documentViewModel: DocumentViewModel,
    onNavigate: (String) -> Unit
) {
    val documentId  = documentViewModel.selectedDocument.collectAsState().value?.id ?: 0
    var productName by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AddPositionTopBar(onNavigate = onNavigate) },
        content = { paddingValues ->
            AddPositionContent(
                productName = productName,
                onProductNameChange = { productName = it },
                unit = unit,
                onUnitChange = { unit = it },
                quantity = quantity,
                onQuantityChange = { quantity = it },
                documentId = documentId,
                documentViewModel = documentViewModel,
                onNavigate = onNavigate,
                paddingValues = paddingValues
            )
        }
    )
}

@Composable
fun AddPositionTopBar(onNavigate: (String) -> Unit) {
    TopAppBarBack("Add New Position") { route -> onNavigate(route) }
}

@Composable
fun AddPositionContent(
    productName: String,
    onProductNameChange: (String) -> Unit,
    unit: String,
    onUnitChange: (String) -> Unit,
    quantity: String,
    onQuantityChange: (String) -> Unit,
    documentId: Int,
    documentViewModel: DocumentViewModel,
    onNavigate: (String) -> Unit,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        PositionInputFields(
            productName = productName,
            onProductNameChange = onProductNameChange,
            unit = unit,
            onUnitChange = onUnitChange,
            quantity = quantity,
            onQuantityChange = onQuantityChange
        )
        Spacer(modifier = Modifier.height(16.dp))
        AddPositionButton(
            productName = productName,
            unit = unit,
            quantity = quantity,
            documentId = documentId,
            documentViewModel = documentViewModel,
            onNavigate = onNavigate
        )
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
    Column {
        TextField(
            value = productName,
            onValueChange = onProductNameChange,
            label = { Text("Product Name") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        TextField(
            value = unit,
            onValueChange = onUnitChange,
            label = { Text("Unit") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
        TextField(
            value = quantity,
            onValueChange = onQuantityChange,
            label = { Text("Quantity") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )
    }
}

@Composable
fun AddPositionButton(
    productName: String,
    unit: String,
    quantity: String,
    documentId: Int,
    documentViewModel: DocumentViewModel,
    onNavigate: (String) -> Unit
) {
    val isQuantityValid = quantity.toIntOrNull() != null

    Button(
        onClick = {
            if (productName.isNotEmpty() && unit.isNotEmpty() && isQuantityValid) {
                val newPosition = Position(
                    productName = productName,
                    documentId = documentId,
                    unit = unit,
                    quantity = quantity.toInt()
                )
                documentViewModel.insertPosition(newPosition)
                onNavigate("back")
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("Add Position", fontSize = 18.sp)
    }
}