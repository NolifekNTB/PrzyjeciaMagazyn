package com.example.przyjeciamagazyn.Documents.presentation.screens.DocumentPositions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.Core.presentation.Navigation.EditScreens
import com.example.przyjeciamagazyn.Documents.presentation.DocumentViewModel
import androidx.compose.material.*
import androidx.compose.material3.*
import com.example.przyjeciamagazyn.Core.presentation.Shared.TopAppBarBack

@Composable
fun PositionDetailScreen(receiptViewModel: DocumentViewModel, onNavigate: (String) -> Unit) {
    val position = receiptViewModel.selectedDocumentPosition.collectAsState().value ?: return

    Scaffold(
        topBar = {
            TopAppBarBack("Receipt Position") { route -> onNavigate(route) }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(20.dp)
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    elevation = 4.dp
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
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onNavigate(EditScreens.EditPositionScreen.route) },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "Edytuj pozycję", fontSize = 18.sp)
                }
            }
        }
    )
}