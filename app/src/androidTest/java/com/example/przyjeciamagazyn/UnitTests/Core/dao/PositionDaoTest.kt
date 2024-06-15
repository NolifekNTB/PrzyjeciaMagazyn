package com.example.przyjeciamagazyn.UnitTests.Core.dao

import kotlinx.coroutines.ExperimentalCoroutinesApi

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.przyjeciamagazyn.Core.data.ROOM.AppDatabase
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.PositionDao
import com.example.przyjeciamagazyn.Documents.data.model.Position
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class PositionDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var positionDao: PositionDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        positionDao = database.documentPositionDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetPositionById() = runBlocking {
        val position = Position(id = 1, documentId = 1, productName = "Product 1", unit = "kg", quantity = 10)
        positionDao.insertPosition(position)

        val retrievedPosition = positionDao.getPosition(1).first()
        assertEquals(position, retrievedPosition)
    }

    @Test
    fun getPositionsForDocument() = runBlocking {
        val position1 = Position(id = 1, documentId = 1, productName = "Product 1", unit = "kg", quantity = 10)
        val position2 = Position(id = 2, documentId = 1, productName = "Product 2", unit = "kg", quantity = 20)
        positionDao.insertPosition(position1)
        positionDao.insertPosition(position2)

        val positions = positionDao.getPositionsForDocument(1).first()
        assertEquals(2, positions.size)
        assertTrue(positions.contains(position1))
        assertTrue(positions.contains(position2))
    }

    @Test
    fun updatePosition() = runBlocking {
        val position = Position(id = 1, documentId = 1, productName = "Product 1", unit = "kg", quantity = 10)
        positionDao.insertPosition(position)

        val updatedPosition = Position(id = 1, documentId = 1, productName = "Updated Product", unit = "kg", quantity = 15)
        positionDao.updatePosition(updatedPosition)

        val retrievedPosition = positionDao.getPosition(1).first()
        assertEquals(updatedPosition, retrievedPosition)
    }
}