package com.example.przyjeciamagazyn.presentation.document

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.data.model.DocumentList
import com.example.przyjeciamagazyn.data.repository.sampleDocuments
import com.example.przyjeciamagazyn.presentation.theme.PrzyjeciaMagazynTheme

@Composable
fun DocumentListViewScreen(documents: List<DocumentList>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(documents) { DocumentList ->
            DocumentRow(DocumentList)
        }
    }
}

@Composable
fun DocumentRow(DocumentList: DocumentList) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { /* TODO: Navigate to DocumentList detail view */ }
    ) {
        Text(text = "Data: ${DocumentList.date}", fontSize = 18.sp)
        Text(text = "Symbol: ${DocumentList.symbol}", fontSize = 18.sp)
        Text(text = "Kontrahent: ${DocumentList.contractor}", fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun ListDocumentsScreenPreview() {
    PrzyjeciaMagazynTheme {
        DocumentListViewScreen(documents = sampleDocuments)
    }
}