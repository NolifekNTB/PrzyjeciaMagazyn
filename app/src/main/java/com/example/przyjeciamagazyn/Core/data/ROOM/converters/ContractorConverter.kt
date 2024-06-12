package com.example.przyjeciamagazyn.Core.data.ROOM.converters

import androidx.room.TypeConverter
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ContractorConverter {
    private val gson = Gson()
    private val type = object : TypeToken<List<Contractor>>() {}.type

    @TypeConverter
    fun fromContractor(contractors: List<Contractor>): String {
        return gson.toJson(contractors, type)
    }

    @TypeConverter
    fun toContractor(contractorString: String?): List<Contractor?> {
        return if (contractorString.isNullOrEmpty()) {
            emptyList()
        } else {
            gson.fromJson(contractorString, type)
        }
    }
}