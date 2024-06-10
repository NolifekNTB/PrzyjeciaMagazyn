package com.example.przyjeciamagazyn.Core.data

import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptDocument
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptPosition

// Sample data for preview and testing
val sampleDocuments = listOf(
    ReceiptDocument(
        date = "2024-06-01",
        symbol = "PZ/1/2024",
        contractors = listOf(Contractor("ABC1", "ABC Sp. z o.o.")),
        positions = listOf(
            ReceiptPosition(receiptId = 0, productName = "Towar A", unit = "szt", quantity =  10),
            ReceiptPosition(receiptId = 0, productName = "Towar B", unit = "szt", quantity =  5),
            ReceiptPosition(receiptId = 0, productName = "Towar C", unit = "szt", quantity =  3)
        )
    ),
    ReceiptDocument(
        date = "2024-06-02",
        symbol = "PZ/2/2024",
        contractors = listOf(Contractor("ABC2", "ABC Sp. z o.o.")),
        positions = listOf(
            ReceiptPosition(receiptId = 0, productName = "Towar A", unit = "szt", quantity =  5),
            ReceiptPosition(receiptId = 0, productName = "Towar B", unit = "szt", quantity =  10)
        )
    )
)

// Sample data for preview and testing
val sampleContractors = listOf(
    Contractor("ABC1", "ABC Sp. z o.o."),
    Contractor("XYZ2", "XYZ S.A."),
    Contractor("DEF3", "DEF Ltd.")
)
