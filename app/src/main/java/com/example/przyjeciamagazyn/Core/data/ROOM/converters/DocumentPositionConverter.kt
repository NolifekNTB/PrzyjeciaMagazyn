package com.example.przyjeciamagazyn.Core.data.ROOM.converters

import androidx.room.TypeConverter
import com.example.przyjeciamagazyn.Documents.data.model.DocumentPosition
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DocumentPositionConverter {
    @TypeConverter
    fun fromPositionList(position: List<DocumentPosition>): String {
        val gson = Gson()
        val type = object : TypeToken<List<DocumentPosition>>() {}.type
        return gson.toJson(position, type)
    }

    @TypeConverter
    fun toPositionList(positionString: String): List<DocumentPosition> {
        val gson = Gson()
        val type = object : TypeToken<List<DocumentPosition>>() {}.type
        return gson.fromJson(positionString, type)
    }
}