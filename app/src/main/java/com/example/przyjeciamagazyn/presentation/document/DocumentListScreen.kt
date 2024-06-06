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
import com.example.przyjeciamagazyn.presentation.main.Screen
import com.example.przyjeciamagazyn.presentation.main.theme.PrzyjeciaMagazynTheme

@Composable
fun DocumentListScreen(documents: List<DocumentList>, onNavigate: (String) -> Unit, ) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(documents) { DocumentList ->
            DocumentRow(DocumentList) { route -> onNavigate(route) }
        }
    }
}

@Composable
fun DocumentRow(DocumentList: DocumentList, onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onNavigate(Screen.DocumentDetailScreen.route) }
    ) {
        Text(text = "Data: ${DocumentList.date}", fontSize = 18.sp)
        Text(text = "Symbol: ${DocumentList.symbol}", fontSize = 18.sp)
        Text(text = "Kontrahent: ${DocumentList.contractor}", fontSize = 18.sp)
    }
}
