package com.example.przyjeciamagazyn.Documents.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Core.data.ROOM.converters.ContractorConverter
import com.example.przyjeciamagazyn.Core.data.ROOM.converters.DocumentPositionConverter

@Entity(tableName = "documents")
data class Document(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val symbol: String,
    @TypeConverters(ContractorConverter::class) val contractors: List<Contractor>,
    @TypeConverters(DocumentPositionConverter::class) val positions: List<Position>
)