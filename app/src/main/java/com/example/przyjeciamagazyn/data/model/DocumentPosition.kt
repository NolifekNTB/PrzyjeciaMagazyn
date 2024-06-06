package com.example.przyjeciamagazyn.data.model

data class DocumentPosition(
    val productName: String,
    val unit: String,
    val quantity: Int
)

data class Document(
    val date: String,
    val symbol: String,
    val contractor: String,
    val positions: List<DocumentPosition>
)