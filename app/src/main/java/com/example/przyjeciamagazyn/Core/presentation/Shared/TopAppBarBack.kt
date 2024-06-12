package com.example.przyjeciamagazyn.Core.presentation.Shared

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBarBack(
    screenTitle: String,
    onNavigateBack: (String) -> Unit
) {
    TopAppBar(
        title = { TopAppBarTitle(screenTitle) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        navigationIcon = { BackNavigationIcon( ) { onNavigateBack("back")} },
    )
}

@Composable
fun TopAppBarTitle(screenTitle: String) {
    Text(
        text = screenTitle,
        fontSize = 20.sp
    )
}

@Composable
fun BackNavigationIcon(onNavigateBack: () -> Unit) {
    IconButton(onClick = { onNavigateBack() }) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier.size(25.dp)
        )
    }
}