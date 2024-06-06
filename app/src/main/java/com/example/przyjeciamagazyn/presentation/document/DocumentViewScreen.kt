package com.example.przyjeciamagazyn.presentation.document

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.data.model.Document
import com.example.przyjeciamagazyn.data.model.DocumentPosition
import com.example.przyjeciamagazyn.data.repository.sampleDocument
import com.example.przyjeciamagazyn.presentation.theme.PrzyjeciaMagazynTheme

@Composable
fun DocumentViewScreen(document: Document) {
    Column(modifier = Modifier.fillMaxSize().padding(25.dp)) {
        Text(text = "Data: ${document.date}", fontSize = 18.sp)
        Text(text = "Symbol: ${document.symbol}", fontSize = 18.sp)
        Text(text = "Kontrahent: ${document.contractor}", fontSize = 18.sp)

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(document.positions) { position ->
                DocumentPositionRow(position)
            }
        }
    }
}

@Composable
fun DocumentPositionRow(position: DocumentPosition) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { /* Navigate to Document Position detail view */ }
    ) {
        Text(text = "Nazwa towaru: ${position.productName}", fontSize = 18.sp)
        Text(text = "Jednostka miary: ${position.unit}", fontSize = 18.sp)
        Text(text = "Ilość: ${position.quantity}", fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DocumentViewScreenPreview() {
    PrzyjeciaMagazynTheme {
        DocumentViewScreen(document = sampleDocument)
    }
}