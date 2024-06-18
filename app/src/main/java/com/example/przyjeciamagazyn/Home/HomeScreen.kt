package com.example.przyjeciamagazyn.Home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.Core.presentation.Navigation.Screen

@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    HomeScreenContent(
        onDocumentListClick = { onNavigate(Screen.DocumentListScreen.route) },
        onContractorListClick = { onNavigate(Screen.ContractorListScreen.route) }
    )
}

@Composable
fun HomeScreenContent(
    onDocumentListClick: () -> Unit,
    onContractorListClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenTitle(text = "Przyjecia Magazyn")
        Spacer(modifier = Modifier.height(16.dp))
        NavigationButton(
            text = "Lista Dokumentów",
            onClick = onDocumentListClick
        )
        NavigationButton(
            text = "Kontrahenci",
            onClick = onContractorListClick
        )
    }
}

@Composable
fun ScreenTitle(text: String) {
    Text(
        text = text,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}

@Composable
fun NavigationButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = text, fontSize = 18.sp)
    }
}