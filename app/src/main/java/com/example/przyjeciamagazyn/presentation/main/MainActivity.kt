package com.example.przyjeciamagazyn.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.przyjeciamagazyn.presentation.main.theme.PrzyjeciaMagazynTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            PrzyjeciaMagazynTheme {
                val navController = rememberNavController()
                DocumentNavGraph(navController = navController)
            }
        }
    }
}
