package com.example.przyjeciamagazyn.Core.data.ROOM.converters

import androidx.room.TypeConverter
import com.example.przyjeciamagazyn.Documents.data.model.Position
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DocumentPositionConverter {
    private val gson = Gson()
    private val type = object : TypeToken<List<Position>>() {}.type

    @TypeConverter
    fun fromPositionList(position: List<Position>): String {
        return gson.toJson(position, type)
    }

    @TypeConverter
    fun toPositionList(positionString: String?): List<Position> {
        return if (positionString.isNullOrEmpty()) {
            emptyList()
        } else {
            gson.fromJson(positionString, type)
        }
    }
}