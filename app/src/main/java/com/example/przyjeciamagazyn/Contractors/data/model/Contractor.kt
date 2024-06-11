package com.example.przyjeciamagazyn.Contractors.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contractors")
data class Contractor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val symbol: String,
    val name: String
)