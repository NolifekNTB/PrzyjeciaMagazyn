package com.example.przyjeciamagazyn.presentation.document

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.przyjeciamagazyn.data.model.DocumentPosition
import com.example.przyjeciamagazyn.data.repository.sampleDocumentPosition
import com.example.przyjeciamagazyn.presentation.main.theme.PrzyjeciaMagazynTheme

@Composable
fun DocumentPositionDetailScreen(position: DocumentPosition) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Nazwa towaru: ${position.productName}", fontSize = 18.sp)
        Text(text = "Jednostka miary: ${position.unit}", fontSize = 18.sp)
        Text(text = "Ilość: ${position.quantity}", fontSize = 18.sp)
    }
}