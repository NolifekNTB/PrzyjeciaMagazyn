package com.example.przyjeciamagazyn.data.repository

import com.example.przyjeciamagazyn.data.model.Contractor
import com.example.przyjeciamagazyn.data.model.Document
import com.example.przyjeciamagazyn.data.model.DocumentList
import com.example.przyjeciamagazyn.data.model.DocumentPosition

// Sample data for preview and testing
val sampleDocument = Document(
    date = "2024-06-01",
    symbol = "PZ/1/2024",
    contractor = "ABC Sp. z o.o.",
    positions = listOf(
        DocumentPosition("Towar A", "szt", 10),
        DocumentPosition("Towar B", "kg", 5),
        DocumentPosition("Towar C", "l", 3)
    )
)

// Sample data for preview and testing
val sampleDocuments = listOf(
    DocumentList("2024-06-01", "PZ/1/2024", "ABC Sp. z o.o."),
    DocumentList("2024-06-02", "PZ/2/2024", "XYZ S.A."),
    DocumentList("2024-06-03", "PZ/3/2024", "DEF Ltd.")
)

// Sample data for preview and testing
val sampleDocumentPosition = DocumentPosition(
    productName = "Towar A",
    unit = "szt",
    quantity = 10
)

// Sample data for preview and testing
val sampleContractors = listOf(
    Contractor("ABC", "ABC Sp. z o.o."),
    Contractor("XYZ", "XYZ S.A."),
    Contractor("DEF", "DEF Ltd.")
)