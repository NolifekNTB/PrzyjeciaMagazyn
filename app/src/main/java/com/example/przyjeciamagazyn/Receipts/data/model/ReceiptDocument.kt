package com.example.przyjeciamagazyn.Receipts.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Core.data.ROOM.Converter.ContractorConverter
import com.example.przyjeciamagazyn.Core.data.ROOM.Converter.ReceiptPositionConverter

@Entity(tableName = "receipts")
data class ReceiptDocument(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val symbol: String,
    @TypeConverters(ContractorConverter::class) val contractors: List<Contractor>,
    @TypeConverters(ReceiptPositionConverter::class) val positions: List<ReceiptPosition>
)