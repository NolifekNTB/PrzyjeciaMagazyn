package com.example.przyjeciamagazyn.Core.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BackButton(nameOfTheScreen: String, modifier: Modifier, onNavigate: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.clickable { onNavigate("back") }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = nameOfTheScreen,
            style = MaterialTheme.typography.h6,
            color = Color.Black
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun topAppBarBack(nameOfTheScreen: String, onNavigate: (String) -> Unit){
    TopAppBar(
        title = { Text(nameOfTheScreen, fontSize = 20.sp) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        navigationIcon = {
            IconButton(onClick = { onNavigate("back") }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", Modifier.size(25.dp))
            }
        },
    )
}