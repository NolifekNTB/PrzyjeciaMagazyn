package com.example.przyjeciamagazyn.UnitTests.Core.converters

import com.example.przyjeciamagazyn.Core.data.ROOM.converters.DocumentPositionConverter
import com.example.przyjeciamagazyn.Documents.data.model.Position
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert.assertEquals
import org.junit.Test

class DocumentPositionConverterTest {

    private val gson = Gson()
    private val type = object : TypeToken<List<Position>>() {}.type
    private val converter = DocumentPositionConverter()

    @Test
    fun testFromPositionList() {
        val positions = listOf(
            Position(id = 1, documentId = 1, productName = "Product 1", unit = "kg", quantity = 10),
            Position(id = 2, documentId = 1, productName = "Product 2", unit = "kg", quantity = 20)
        )
        val expectedJson = gson.toJson(positions, type)
        val jsonResult = converter.fromPositionList(positions)
        assertEquals(expectedJson, jsonResult)
    }

    @Test
    fun testToPositionList() {
        val positions = listOf(
            Position(id = 1, documentId = 1, productName = "Product 1", unit = "kg", quantity = 10),
            Position(id = 2, documentId = 1, productName = "Product 2", unit = "kg", quantity = 20)
        )
        val json = gson.toJson(positions, type)
        val positionListResult = converter.toPositionList(json)
        assertEquals(positions, positionListResult)
    }

    @Test
    fun testToPositionListWithNullOrEmpty() {
        val emptyList = emptyList<Position>()
        val nullResult = converter.toPositionList(null)
        val emptyResult = converter.toPositionList("")
        assertEquals(emptyList, nullResult)
        assertEquals(emptyList, emptyResult)
    }
}
