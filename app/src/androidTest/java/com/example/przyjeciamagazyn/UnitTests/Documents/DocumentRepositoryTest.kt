package com.example.przyjeciamagazyn.UnitTests.Documents

import android.content.Context
import com.example.przyjeciamagazyn.Core.data.ROOM.AppDatabase
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.DocumentDao
import com.example.przyjeciamagazyn.Documents.data.model.Document
import com.example.przyjeciamagazyn.Documents.data.model.Position
import com.example.przyjeciamagazyn.Documents.data.repository.DocumentRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import io.mockk.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class DocumentRepositoryTest {

    private lateinit var documentDao: DocumentDao
    private lateinit var repository: DocumentRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        documentDao = mockk()
        val context = mockk<Context>()

        every { context.getSystemService(any()) } returns null

        mockkStatic(AppDatabase::class)
        every { AppDatabase.getInstance(context).documentDao() } returns documentDao

        repository = DocumentRepository(context).apply {
            document = this@DocumentRepositoryTest.documentDao
        }
    }

    @Test
    fun `getAllDocuments returns flow from DAO`() = runBlocking {
        // Arrange
        val documents = listOf(
            Document(id = 1, date = "2023-01-01", symbol = "DOC1", contractors = emptyList(), positions = emptyList()),
            Document(id = 2, date = "2023-01-02", symbol = "DOC2", contractors = emptyList(), positions = emptyList())
        )
        coEvery { documentDao.getAllDocuments() } returns flowOf(documents)

        // Actz
        val result = repository.getAllDocuments().first()

        // Assert
        assertEquals(documents, result)
        coVerify { documentDao.getAllDocuments() }
    }

    @Test
    fun `getDocument returns flow from DAO`() = runBlocking {
        // Arrange
        val document = Document(id = 1, date = "2023-01-01", symbol = "DOC1", contractors = emptyList(), positions = emptyList())
        coEvery { documentDao.getDocument(1) } returns flowOf(document)

        // Act
        val result = repository.getDocument(1).first()

        // Assert
        assertEquals(document, result)
        coVerify { documentDao.getDocument(1) }
    }

    @Test
    fun `getDocumentsContainingContractor returns flow from DAO`() = runBlocking {
        // Arrange
        val documents = listOf(
            Document(id = 1, date = "2023-01-01", symbol = "DOC1", contractors = emptyList(), positions = emptyList())
        )
        coEvery { documentDao.getDocumentsContainingContractor(1) } returns flowOf(documents)

        // Act
        val result = repository.getDocumentsContainingContractor(1).first()

        // Assert
        assertEquals(documents, result)
        coVerify { documentDao.getDocumentsContainingContractor(1) }
    }

    @Test
    fun `insertDocument calls DAO`() = runBlocking {
        // Arrange
        val document = Document(id = 1, date = "2023-01-01", symbol = "DOC1", contractors = emptyList(), positions = emptyList())
        coEvery { documentDao.insertDocument(document) } just Runs

        // Act
        repository.insertDocument(document)

        // Assert
        coVerify { documentDao.insertDocument(document) }
    }

    @Test
    fun `updateDocument calls DAO`() = runBlocking {
        // Arrange
        val updatedDocument = Document(id = 1, date = "2023-01-01", symbol = "DOC1", contractors = emptyList(), positions = emptyList())
        coEvery { documentDao.updateDocument(updatedDocument) } just Runs

        // Act
        repository.updateDocument(updatedDocument)

        // Assert
        coVerify { documentDao.updateDocument(updatedDocument) }
    }

    @Test
    fun `updateDocumentPositions calls DAO`() = runBlocking {
        // Arrange
        val positions = listOf(
            Position(id = 1, documentId = 1, productName = "Product 1", unit = "kg", quantity = 10)
        )
        coEvery { documentDao.updateDocumentPositions(1, positions) } just Runs

        // Act
        repository.updateDocumentPositions(1, positions)

        // Assert
        coVerify { documentDao.updateDocumentPositions(1, positions) }
    }

    @Test
    fun `deleteAllDocuments calls DAO`() = runBlocking {
        coEvery { documentDao.deleteAllDocuments() } just Runs

        // Act
        repository.deleteAllDocuments()

        // Assert
        coVerify { documentDao.deleteAllDocuments() }
    }
}