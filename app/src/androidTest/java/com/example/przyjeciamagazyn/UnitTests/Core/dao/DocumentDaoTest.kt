package com.example.przyjeciamagazyn.UnitTests.Core.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Core.data.ROOM.AppDatabase
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.DocumentDao
import com.example.przyjeciamagazyn.Documents.data.model.Document
import com.example.przyjeciamagazyn.Documents.data.model.Position
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class DocumentDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var documentDao: DocumentDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries() // Only for testing purposes
            .build()
        documentDao = database.documentDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetAllDocuments() = runBlocking {
        val document = Document(id = 1, date = "2023-01-01", symbol = "DOC1", contractors = emptyList(), positions = emptyList())
        documentDao.insertDocument(document)

        val documents = documentDao.getAllDocuments().first()
        assertEquals(1, documents.size)
        assertEquals(document, documents[0])
    }

    @Test
    fun getDocumentById() = runBlocking {
        val document = Document(id = 1, date = "2023-01-01", symbol = "DOC1", contractors = emptyList(), positions = emptyList())
        documentDao.insertDocument(document)

        val retrievedDocument = documentDao.getDocument(1).first()
        assertEquals(document, retrievedDocument)
    }

    @Test
    fun updateDocumentPositions() = runBlocking {
        val document = Document(id = 1, date = "2023-01-01", symbol = "DOC1", contractors = emptyList(), positions = emptyList())
        documentDao.insertDocument(document)

        val updatedPositions = listOf(Position(id = 1, documentId = 1, productName = "Product 1", unit = "kg", quantity = 10))
        documentDao.updateDocumentPositions(1, updatedPositions)

        val retrievedDocument = documentDao.getDocument(1).first()
        assertEquals(updatedPositions, retrievedDocument.positions)
    }

    @Test
    fun updateDocument() = runBlocking {
        val document = Document(id = 1, date = "2023-01-01", symbol = "DOC1", contractors = emptyList(), positions = emptyList())
        documentDao.insertDocument(document)

        val updatedDocument = Document(id = 1, date = "2023-01-02", symbol = "DOC2", contractors = emptyList(), positions = emptyList())
        documentDao.updateDocument(updatedDocument)

        val retrievedDocument = documentDao.getDocument(1).first()
        assertEquals(updatedDocument, retrievedDocument)
    }

    @Test
    fun deleteAllDocuments() = runBlocking {
        val document1 = Document(id = 1, date = "2023-01-01", symbol = "DOC1", contractors = emptyList(), positions = emptyList())
        val document2 = Document(id = 2, date = "2023-01-02", symbol = "DOC2", contractors = emptyList(), positions = emptyList())
        documentDao.insertDocument(document1)
        documentDao.insertDocument(document2)

        documentDao.deleteAllDocuments()

        val documents = documentDao.getAllDocuments().first()
        assertTrue(documents.isEmpty())
    }
}