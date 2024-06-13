package com.example.przyjeciamagazyn.Documents.presentation.screens.DocumentPositions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.Core.presentation.Navigation.AddScreens
import com.example.przyjeciamagazyn.Core.presentation.Navigation.EditScreens
import com.example.przyjeciamagazyn.Documents.data.model.Position
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Screen
import com.example.przyjeciamagazyn.Core.presentation.Shared.TopAppBarBack
import com.example.przyjeciamagazyn.Documents.data.model.Document
import com.example.przyjeciamagazyn.Documents.presentation.DocumentViewModel

@Composable
fun PositionsListScreen(receiptViewModel: DocumentViewModel, onNavigate: (String) -> Unit) {
    val receipt = receiptViewModel.selectedDocument.collectAsState().value ?: return

    Scaffold(
        topBar = { PositionsListTopBar(onNavigate = onNavigate) },
        content = { paddingValues ->
            PositionsListContent(
                receipt = receipt,
                onNavigate = onNavigate,
                paddingValues = paddingValues,
                onPositionClick = { position ->
                    receiptViewModel.selectedDocumentPosition.value = position
                    onNavigate(Screen.PositionDetailScreen.route)
                }
            )
        }
    )
}

@Composable
fun PositionsListTopBar(onNavigate: (String) -> Unit) {
    TopAppBarBack("Receipt Positions") { route -> onNavigate(route) }
}

@Composable
fun PositionsListContent(
    receipt: Document,
    onNavigate: (String) -> Unit,
    paddingValues: PaddingValues,
    onPositionClick: (Position) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        ReceiptInfo(receipt)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(receipt.positions) { position ->
                DocumentPositionRow(position, onPositionClick)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        EditDocumentButton { onNavigate(EditScreens.EditDocumentScreen.route) }
        AddPositionButton { onNavigate(AddScreens.AddPositionScreen.route) }
    }
}

@Composable
fun ReceiptInfo(receipt: Document) {
    Text(text = "Data: ${receipt.date}", fontSize = 18.sp, modifier = Modifier.padding(vertical = 4.dp))
    Text(text = "Symbol: ${receipt.symbol}", fontSize = 18.sp, modifier = Modifier.padding(vertical = 4.dp))
    Text(text = "Kontrahent: ${receipt.contractors.joinToString { it.name }}", fontSize = 18.sp, modifier = Modifier.padding(vertical = 4.dp))
}


@Composable
fun DocumentPositionRow(position: Position, onNavigate: (Position) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onNavigate(position) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Nazwa towaru: ${position.productName}", fontSize = 18.sp)
            Text(text = "Jednostka miary: ${position.unit}", fontSize = 18.sp)
            Text(text = "Ilość: ${position.quantity}", fontSize = 18.sp)
        }
    }
}

@Composable
fun EditDocumentButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = "Edytuj dokument", fontSize = 18.sp)
    }
}

@Composable
fun AddPositionButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = "Dodaj pozycje", fontSize = 18.sp)
    }
}