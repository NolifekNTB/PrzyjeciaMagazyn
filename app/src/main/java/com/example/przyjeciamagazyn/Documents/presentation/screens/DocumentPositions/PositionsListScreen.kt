package com.example.przyjeciamagazyn.Documents.presentation.screens.DocumentPositions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import com.example.przyjeciamagazyn.Documents.data.model.DocumentPosition
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Screen
import com.example.przyjeciamagazyn.Core.presentation.Shared.TopAppBarBack
import com.example.przyjeciamagazyn.Documents.presentation.DocumentViewModel

@Composable
fun PositionsListScreen(receiptViewModel: DocumentViewModel, onNavigate: (String) -> Unit) {
    val receipt = receiptViewModel.selectedDocument.collectAsState().value ?: return

    Scaffold(
        topBar = {
            TopAppBarBack("Receipt Positions") { route -> onNavigate(route) }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(15.dp))

                Text(text = "Data: ${receipt.date}", fontSize = 18.sp, modifier = Modifier.padding(vertical = 4.dp))
                Text(text = "Symbol: ${receipt.symbol}", fontSize = 18.sp, modifier = Modifier.padding(vertical = 4.dp))
                Text(text = "Kontrahent: ${receipt.contractors.joinToString { it.name }}", fontSize = 18.sp, modifier = Modifier.padding(vertical = 4.dp))

                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(receipt.positions) { position ->
                        DocumentPositionRow(position) { receiptPosition ->
                            receiptViewModel.selectedDocumentPosition.value = receiptPosition
                            onNavigate(Screen.PositionDetailScreen.route)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onNavigate(EditScreens.EditDocumentScreen.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = "Edytuj dokument", fontSize = 18.sp)
                }

                Button(
                    onClick = { onNavigate(AddScreens.AddPositionScreen.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = "Dodaj pozycje", fontSize = 18.sp)
                }
            }
        }
    )
}

@Composable
fun DocumentPositionRow(position: DocumentPosition, onNavigate: (DocumentPosition) -> Unit) {
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