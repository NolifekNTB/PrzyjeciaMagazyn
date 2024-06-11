package com.example.przyjeciamagazyn.Receipts.presentation.screens.ReceiptDetail

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.Core.presentation.Navigation.AddScreens
import com.example.przyjeciamagazyn.Core.presentation.BackButton
import com.example.przyjeciamagazyn.Core.presentation.Navigation.EditScreens
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptPosition
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Screen
import com.example.przyjeciamagazyn.Receipts.presentation.ReceiptViewModel

@Composable
fun DocumentDetailScreen(receiptViewModel: ReceiptViewModel, onNavigate: (String) -> Unit) {
    val receipt = receiptViewModel.selectedDocument.collectAsState().value ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        BackButton("Receipt Positions",Modifier.padding(top = 5.dp)) {route -> onNavigate(route)}
        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Data: ${receipt.date}", fontSize = 18.sp)
        Text(text = "Symbol: ${receipt.symbol}", fontSize = 18.sp)
        Text(text = "Kontrahent: ${receipt.contractors}", fontSize = 18.sp)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(receipt.positions) { position ->
                DocumentPositionRow(position) { receiptPosition ->
                    receiptViewModel.selectedDocumentPosition.value = receiptPosition
                    onNavigate(Screen.DocumentPositionDetailScreen.route)
                }
            }
        }

        Button(
            onClick = { onNavigate(EditScreens.EditDocumentSheet.route) },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(16.dp))
        {
            Text(text = "Edytuj dokument")
        }

        Button(
            onClick = { onNavigate(AddScreens.AddPositionSheet.route) },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(16.dp))
        {
            Text(text = "Dodaj pozycje")
        }


    }
}

@Composable
fun DocumentPositionRow(position: ReceiptPosition, onNavigate: (ReceiptPosition) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onNavigate(position) }
    ) {
        Text(text = "Nazwa towaru: ${position.productName}", fontSize = 18.sp)
        Text(text = "Jednostka miary: ${position.unit}", fontSize = 18.sp)
        Text(text = "Ilość: ${position.quantity}", fontSize = 18.sp)
    }
}