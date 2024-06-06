package com.example.przyjeciamagazyn.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.presentation.contractor.ContractorListViewScreen
import com.example.przyjeciamagazyn.presentation.theme.PrzyjeciaMagazynTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            PrzyjeciaMagazynTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { /* TODO Navigate to Lista Dokumentów */ }) {
            Text(text = "Lista Dokumentów", fontSize = 18.sp)
        }
        Button(onClick = { /* TODO Navigate to Kontrahenci */ }) {
            Text(text = "Kontrahenci", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    PrzyjeciaMagazynTheme {
        MainScreen()
    }
}