package com.example.przyjeciamagazyn.UnitTests.Documents

import android.content.Context
import com.example.przyjeciamagazyn.Core.data.ROOM.AppDatabase
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.PositionDao
import com.example.przyjeciamagazyn.Documents.data.model.Position
import com.example.przyjeciamagazyn.Documents.data.repository.PositionsRepository
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class PositionsRepositoryTest {

    private lateinit var positionDao: PositionDao
    private lateinit var repository: PositionsRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        positionDao = mockk()
        val context = mockk<Context>()

        every { context.getSystemService(any()) } returns null

        mockkStatic(AppDatabase::class)
        every { AppDatabase.getInstance(context).documentPositionDao() } returns positionDao

        repository = PositionsRepository(context).apply {
            documentPosition = this@PositionsRepositoryTest.positionDao
        }
    }

    @Test
    fun `getPosition returns flow from DAO`() = runBlocking {
        // Arrange
        val position = Position(id = 1, documentId = 1, productName = "Product 1", unit = "kg", quantity = 10)
        coEvery { positionDao.getPosition(1) } returns flowOf(position)

        // Act
        val result = repository.getPosition(1).first()

        // Assert
        assertEquals(position, result)
        coVerify { positionDao.getPosition(1) }
    }

    @Test
    fun `getPositionsForDocument returns flow from DAO`() = runBlocking {
        // Arrange
        val positions = listOf(
            Position(id = 1, documentId = 1, productName = "Product 1", unit = "kg", quantity = 10),
            Position(id = 2, documentId = 1, productName = "Product 2", unit = "kg", quantity = 20)
        )
        coEvery { positionDao.getPositionsForDocument(1) } returns flowOf(positions)

        // Act
        val result = repository.getPositionsForDocument(1).first()

        // Assert
        assertEquals(positions, result)
        coVerify { positionDao.getPositionsForDocument(1) }
    }

    @Test
    fun `insertPosition calls DAO`() = runBlocking {
        // Arrange
        val position = Position(id = 1, documentId = 1, productName = "Product 1", unit = "kg", quantity = 10)
        coEvery { positionDao.insertPosition(position) } just Runs

        // Act
        repository.insertPosition(position)

        // Assert
        coVerify { positionDao.insertPosition(position) }
    }

    @Test
    fun `updatePosition calls DAO`() = runBlocking {
        // Arrange
        val position = Position(id = 1, documentId = 1, productName = "Product 1", unit = "kg", quantity = 10)
        coEvery { positionDao.updatePosition(position) } just Runs

        // Act
        repository.updatePosition(position)

        // Assert
        coVerify { positionDao.updatePosition(position) }
    }
}