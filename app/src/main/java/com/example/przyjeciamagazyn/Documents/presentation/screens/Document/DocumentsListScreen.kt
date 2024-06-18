package com.example.przyjeciamagazyn.Documents.presentation.screens.Document

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Core.presentation.Navigation.AddScreens
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Screen
import com.example.przyjeciamagazyn.Core.presentation.Shared.TopAppBarBack
import com.example.przyjeciamagazyn.Documents.data.model.Document
import com.example.przyjeciamagazyn.Documents.presentation.DocumentViewModel

@Composable
fun DocumentListScreen(documentViewModel: DocumentViewModel, onNavigate: (String) -> Unit, ) {
    val documents by documentViewModel.documentList.collectAsState(emptyList())
    val selectedDocument by documentViewModel.selectedDocument.collectAsState()

    // Fetch all documents when a new document is selected
    LaunchedEffect(key1 = selectedDocument) {
        documentViewModel.getALlDocuments()
    }

    Scaffold(
        topBar = { DocumentListTopAppBar(onNavigate = onNavigate) },
        floatingActionButton = { AddDocumentButton(onNavigate = onNavigate) },
        content = { paddingValues ->
            DocumentListView(
                documents = documents,
                paddingValues = paddingValues,
                onDocumentClick = { document ->
                    documentViewModel.selectDocument(document)
                    onNavigate(Screen.PositionsListScreen.route)
                }
            )
        }
    )
}

@Composable
fun DocumentListTopAppBar(onNavigate: (String) -> Unit) {
    TopAppBarBack(screenTitle = "document List", onNavigateBack = onNavigate)
}

@Composable
fun AddDocumentButton(onNavigate: (String) -> Unit) {
    FloatingActionButton(onClick = { onNavigate(AddScreens.AddDocumentScreen.route) }) {
        Icon(Icons.Default.Add, contentDescription = "Add Document")
    }
}

@Composable
fun DocumentListView(
    documents: List<Document>,
    paddingValues: PaddingValues,
    onDocumentClick: (Document) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(documents) { documentDocument ->
                DocumentListItem(documentDocument, onDocumentClick)
            }
        }
    }
}


@Composable
fun DocumentListItem(documentDocument: Document, onDocumentClick: (Document) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onDocumentClick(documentDocument) },
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            DocumentDetails(documentDocument)
            ContractorListView(contractors = documentDocument.contractors)
        }
    }
}

@Composable
fun DocumentDetails(documentDocument: Document) {
    Text(
        text = "Date: ${documentDocument.date}",
        style = MaterialTheme.typography.titleMedium
    )
    Text(
        text = "Symbol: ${documentDocument.symbol}",
        style = MaterialTheme.typography.titleMedium
    )
    Text(
        text = "Contractors:",
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun ContractorListView(contractors: List<Contractor>) {
    Spacer(modifier = Modifier.height(8.dp))
    Column {
        contractors.forEach { contractor ->
            ContractorListItem(contractor)
        }
    }
}


@Composable
fun ContractorListItem(contractor: Contractor) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = contractor.symbol,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(100.dp)
        )
        Text(
            text = contractor.name,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
    }
}
