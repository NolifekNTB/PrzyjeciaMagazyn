package com.example.przyjeciamagazyn.Documents.presentation.screens.DocumentPositions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import com.example.przyjeciamagazyn.Documents.data.model.Position
import com.example.przyjeciamagazyn.Documents.presentation.DocumentViewModel
import androidx.compose.material3.Scaffold
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.Core.presentation.Shared.TopAppBarBack

@Composable
fun EditPositionScreen(
    documentViewModel: DocumentViewModel,
    onNavigate: (String) -> Unit
) {
    val position = documentViewModel.selectedPosition.collectAsState().value ?: return

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
        topBar = { EditPositionTopBar(onNavigate = onNavigate) },
        content = { paddingValues ->
            EditPositionContent(
                productName = productName,
                onProductNameChange = { productName = it },
                unit = unit,
                onUnitChange = { unit = it },
                quantity = quantity,
                onQuantityChange = { quantity = it },
                position = position,
                documentViewModel = documentViewModel,
                onNavigate = onNavigate,
                paddingValues = paddingValues
            )
        }
    )
}

@Composable
fun EditPositionTopBar(onNavigate: (String) -> Unit) {
    TopAppBarBack("Edit document Position") { route -> onNavigate(route) }
}

@Composable
fun EditPositionContent(
    productName: String,
    onProductNameChange: (String) -> Unit,
    unit: String,
    onUnitChange: (String) -> Unit,
    quantity: String,
    onQuantityChange: (String) -> Unit,
    position: Position,
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
        EditPositionButton(
            productName = productName,
            unit = unit,
            quantity = quantity,
            position = position,
            documentViewModel = documentViewModel,
            onNavigate = onNavigate
        )
    }
}

@Composable
fun EditPositionButton(
    productName: String,
    unit: String,
    quantity: String,
    position: Position,
    documentViewModel: DocumentViewModel,
    onNavigate: (String) -> Unit
) {
    val isQuantityValid = quantity.toIntOrNull() != null

    Button(
        onClick = {
            if (productName.isNotEmpty() && unit.isNotEmpty() && isQuantityValid) {
                val updatedPosition = Position(
                    id = position.id,
                    documentId = position.documentId,
                    productName = productName,
                    unit = unit,
                    quantity = quantity.toInt()
                )
                documentViewModel.updateDocumentPositions(updatedPosition)
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

