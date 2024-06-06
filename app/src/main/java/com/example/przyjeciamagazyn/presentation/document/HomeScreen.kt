package com.example.przyjeciamagazyn.presentation.document

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.przyjeciamagazyn.presentation.main.Screen

@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { onNavigate (Screen.DocumentListScreen.route) }) {
            Text(text = "Lista Dokumentów", fontSize = 18.sp)
        }
        Button(onClick = { onNavigate (Screen.ContractorListScreen.route) }) {
            Text(text = "Kontrahenci", fontSize = 18.sp)
        }
    }
}