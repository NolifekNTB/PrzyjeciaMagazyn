package com.example.przyjeciamagazyn.Core.data.ROOM.Converter

import androidx.room.TypeConverter
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptPosition
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ReceiptPositionConverter {
    @TypeConverter
    fun fromPositionList(position: List<ReceiptPosition>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ReceiptPosition>>() {}.type
        return gson.toJson(position, type)
    }

    @TypeConverter
    fun toPositionList(positionString: String): List<ReceiptPosition> {
        val gson = Gson()
        val type = object : TypeToken<List<ReceiptPosition>>() {}.type
        return gson.fromJson(positionString, type)
    }
}