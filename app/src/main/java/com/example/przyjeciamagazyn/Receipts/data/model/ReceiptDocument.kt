package com.example.przyjeciamagazyn.Receipts.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.przyjeciamagazyn.Core.data.ROOM.Converter.ReceiptPositionConverter

@Entity(tableName = "receipts")
data class ReceiptDocument(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val symbol: String,
    val contractor: String,
    @TypeConverters(ReceiptPositionConverter::class) val positions: List<ReceiptPosition>
)