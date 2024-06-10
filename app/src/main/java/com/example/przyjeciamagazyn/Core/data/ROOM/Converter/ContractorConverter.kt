package com.example.przyjeciamagazyn.Core.data.ROOM.Converter

import androidx.room.TypeConverter
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ContractorConverter {
    @TypeConverter
    fun fromContractor(contractor: List<Contractor>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Contractor>>() {}.type
        return gson.toJson(contractor, type)
    }

    @TypeConverter
    fun toContractor(contractorString: String?): List<Contractor?> {
        val gson = Gson()
        val type = object : TypeToken<List<Contractor>>() {}.type
        return gson.fromJson(contractorString, type)
    }
}