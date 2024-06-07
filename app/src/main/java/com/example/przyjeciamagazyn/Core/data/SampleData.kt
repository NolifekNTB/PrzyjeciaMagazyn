package com.example.przyjeciamagazyn.Core.data

import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptDocument
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptPosition

// Sample data for preview and testing
val sampleDocuments = listOf(
    ReceiptDocument(
        date = "2024-06-01",
        symbol = "PZ/1/2024",
        contractor = "ABC Sp. z o.o.",
        positions = listOf(
            ReceiptPosition("Towar A", "szt", 10),
            ReceiptPosition("Towar B", "kg", 5),
            ReceiptPosition("Towar C", "l", 3)
        )
    ),
    ReceiptDocument(
        date = "2024-06-02",
        symbol = "PZ/2/2024",
        contractor = "XYZ S.A.",
        positions = listOf(
            ReceiptPosition("Towar A", "szt", 10),
            ReceiptPosition("Towar B", "kg", 5),
            ReceiptPosition("Towar C", "l", 3)
        )
    ),
    ReceiptDocument(
        date = "2024-06-03",
        symbol = "PZ/3/2024",
        contractor = "DEF Ltd.",
        positions = listOf(
            ReceiptPosition("Towar A", "szt", 10),
            ReceiptPosition("Towar B", "kg", 5),
            ReceiptPosition("Towar C", "l", 3)
        )
    )
)

// Sample data for preview and testing
val sampleContractors = listOf(
    Contractor("ABC", "ABC Sp. z o.o."),
    Contractor("XYZ", "XYZ S.A."),
    Contractor("DEF", "DEF Ltd.")
)
