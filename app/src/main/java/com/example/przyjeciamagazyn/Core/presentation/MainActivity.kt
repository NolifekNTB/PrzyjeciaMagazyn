package com.example.przyjeciamagazyn.Core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.przyjeciamagazyn.Core.presentation.Navigation.NavigationNavGraph
import com.example.przyjeciamagazyn.Core.presentation.theme.PrzyjeciaMagazynTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrzyjeciaMagazynTheme {
                val navController = rememberNavController()
                NavigationNavGraph(navController = navController)
            }
        }
    }
}
