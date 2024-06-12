package com.example.przyjeciamagazyn.Receipts.presentation.screens.Receipt

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Core.presentation.Navigation.AddScreens
import com.example.przyjeciamagazyn.Core.presentation.BackButton
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Screen
import com.example.przyjeciamagazyn.Core.presentation.topAppBarBack
import com.example.przyjeciamagazyn.R
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptDocument
import com.example.przyjeciamagazyn.Receipts.presentation.ReceiptViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceiptListScreen(receiptViewModel: ReceiptViewModel, onNavigate: (String) -> Unit, ) {
    val documents = receiptViewModel.receiptDocuments.collectAsState(emptyList()).value
    val selectedDocument = receiptViewModel.selectedDocument

    LaunchedEffect(key1 = documents) {
        receiptViewModel.getALlReceipts()
    }

    Scaffold(
        topBar = {
            topAppBarBack("Receipt List") { route -> onNavigate(route)}
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigate(AddScreens.AddDocumentSheet.route) }) {
                Icon(Icons.Default.Add, contentDescription = "Add Document")
            }
        },
        content = { paddingValues ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)) {
                    LazyColumn(modifier = Modifier.weight(1f)) {
                      items(documents) { receiptDocument ->
                            DocumentRow(receiptDocument) { document ->
                                selectedDocument.value = document
                               onNavigate(Screen.DocumentDetailScreen.route)
                            }
                    }
                }
            }
        }
    )
}

@Composable
fun DocumentRow(receiptDocument: ReceiptDocument, onDocumentClick: (ReceiptDocument) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onDocumentClick(receiptDocument) },
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Date: ${receiptDocument.date}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Symbol: ${receiptDocument.symbol}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Contractors:", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Column {
                receiptDocument.contractors.forEach { contractor ->
                    ContractorRow(contractor)
                }
            }
        }
    }
}

@Composable
fun ContractorRow(contractor: Contractor) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = contractor.symbol,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.width(100.dp)
        )
        Text(
            text = contractor.name,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
