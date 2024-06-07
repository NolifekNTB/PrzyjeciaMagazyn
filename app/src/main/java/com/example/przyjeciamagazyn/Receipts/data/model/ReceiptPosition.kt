package com.example.przyjeciamagazyn.Receipts.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "receipt_positions")
data class ReceiptPosition(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val receiptId: Int,
    val productName: String,
    val unit: String,
    val quantity: Int
)
