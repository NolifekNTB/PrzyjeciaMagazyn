package com.example.przyjeciamagazyn.Documents.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "positions")
data class Position(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val documentId: Int,
    val productName: String,
    val unit: String,
    val quantity: Int
)
