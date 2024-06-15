package com.example.przyjeciamagazyn.UnitTests.Core.converters

import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Core.data.ROOM.converters.ContractorConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert.assertEquals
import org.junit.Test

class ContractorConverterTest {

    private val gson = Gson()
    private val type = object : TypeToken<List<Contractor>>() {}.type
    private val converter = ContractorConverter()

    @Test
    fun testFromContractor() {
        val contractors = listOf(
            Contractor(id = 1, symbol = "CON1", name = "Contractor 1"),
            Contractor(id = 2, symbol = "CON2", name = "Contractor 2")
        )
        val expectedJson = gson.toJson(contractors, type)
        val jsonResult = converter.fromContractor(contractors)
        assertEquals(expectedJson, jsonResult)
    }

    @Test
    fun testToContractor() {
        val contractors = listOf(
            Contractor(id = 1, symbol = "CON1", name = "Contractor 1"),
            Contractor(id = 2, symbol = "CON2", name = "Contractor 2")
        )
        val json = gson.toJson(contractors, type)
        val contractorListResult = converter.toContractor(json)
        assertEquals(contractors, contractorListResult)
    }

    @Test
    fun testToContractorWithNullOrEmpty() {
        val emptyList = emptyList<Contractor>()
        val nullResult = converter.toContractor(null)
        val emptyResult = converter.toContractor("")
        assertEquals(emptyList, nullResult)
        assertEquals(emptyList, emptyResult)
    }
}