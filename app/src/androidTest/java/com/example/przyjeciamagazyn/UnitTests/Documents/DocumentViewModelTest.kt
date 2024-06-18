package com.example.przyjeciamagazyn.UnitTests.Documents

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.przyjeciamagazyn.Documents.data.model.Document
import com.example.przyjeciamagazyn.Documents.data.model.Position
import com.example.przyjeciamagazyn.Documents.data.repository.DocumentRepository
import com.example.przyjeciamagazyn.Documents.data.repository.PositionsRepository
import com.example.przyjeciamagazyn.Documents.presentation.DocumentViewModel
import com.example.przyjeciamagazyn.UnitTests.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class DocumentViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var documentRepository: DocumentRepository
    private lateinit var positionRepository: PositionsRepository
    private lateinit var documentViewModel: DocumentViewModel

    @Before
    fun setUp() {
        documentRepository = mockk()
        positionRepository = mockk()

        // Ensure the mock setup is correct
        coEvery { documentRepository.getAllDocuments() } returns flowOf(emptyList())
        coEvery { documentRepository.insertDocument(any()) } returns Unit
        coEvery { documentRepository.updateDocument(any()) } returns Unit
        coEvery { documentRepository.updateDocumentPositions(any(), any()) } returns Unit
        coEvery { documentRepository.getDocument(any()) } returns flowOf()
        coEvery { positionRepository.insertPosition(any()) } returns Unit
        coEvery { positionRepository.updatePosition(any()) } returns Unit
        coEvery { positionRepository.getPositionsForDocument(any()) } returns flowOf(emptyList())
        coEvery { positionRepository.getPosition(any()) } returns flowOf()

        documentViewModel = DocumentViewModel(documentRepository, positionRepository)
    }

    @Test
    fun testGetAllDocuments() = coroutineTestRule.scope.runTest {
        val documents = listOf(
            Document(id = 1, date = "2023-06-14", symbol = "DOC1", contractors = emptyList(), positions = emptyList()),
            Document(id = 2, date = "2023-06-15", symbol = "DOC2", contractors = emptyList(), positions = emptyList())
        )
        coEvery { documentRepository.getAllDocuments() } returns flowOf(documents)

        documentViewModel.getALlDocuments()
        coroutineTestRule.scope.advanceUntilIdle()

        assertEquals(documents, documentViewModel.documentList.first())
    }

    @Test
    fun testInsertDocument() = coroutineTestRule.scope.runTest {
        val document = Document(id = 1, date = "2023-06-14", symbol = "DOC1", contractors = emptyList(), positions = emptyList())
        coEvery { documentRepository.insertDocument(document) } returns Unit

        documentViewModel.insertDocument(document)
        coroutineTestRule.scope.advanceUntilIdle()

        coVerify { documentRepository.insertDocument(document) }
    }

    @Test
    fun testInsertPosition() = coroutineTestRule.scope.runTest {
        val document = Document(id = 1, date = "2023-06-14", symbol = "DOC1", contractors = emptyList(), positions = emptyList())
        val position = Position(id = 1, documentId = 1, productName = "Product A", unit = "kg", quantity = 10)
        coEvery { positionRepository.insertPosition(position) } returns Unit
        coEvery { positionRepository.getPositionsForDocument(position.documentId) } returns flowOf(listOf(position))
        coEvery { documentRepository.getDocument(position.documentId) } returns flowOf(Document(id = 1, date = "2023-06-14", symbol = "DOC1", contractors = emptyList(), positions = listOf(position)))
        coEvery { positionRepository.getPosition(position.id) } returns flowOf(position)

        documentViewModel.selectDocument(document)
        documentViewModel.insertPosition(position)
        coroutineTestRule.scope.advanceUntilIdle()

        coVerify { positionRepository.insertPosition(position) }
    }

    @Test
    fun testUpdateDocument() = coroutineTestRule.scope.runTest {
        val document = Document(id = 1, date = "2023-06-14", symbol = "DOC1", contractors = emptyList(), positions = emptyList())
        coEvery { documentRepository.updateDocument(document) } returns Unit

        documentViewModel.updateDocument(document)
        coroutineTestRule.scope.advanceUntilIdle()

        coVerify { documentRepository.updateDocument(document) }
        assertEquals(document, documentViewModel.selectedDocument.value)
    }

    @Test
    fun testUpdateDocumentPositions() = coroutineTestRule.scope.runTest {
        val position = Position(id = 1, documentId = 1, productName = "Product A", unit = "kg", quantity = 10)
        val document = Document(id = 1, date = "2023-06-14", symbol = "DOC1", contractors = emptyList(), positions = listOf(position))
        coEvery { positionRepository.updatePosition(position) } returns Unit
        coEvery { positionRepository.getPositionsForDocument(position.documentId) } returns flowOf(listOf(position))
        coEvery { documentRepository.getDocument(position.documentId) } returns flowOf(document)
        coEvery { documentRepository.updateDocumentPositions(any(), any()) } returns Unit
        coEvery { positionRepository.getPosition(position.id) } returns flowOf(position)

        documentViewModel.selectDocument(document)
        documentViewModel.updateDocumentPositions(position)
        coroutineTestRule.scope.advanceUntilIdle()

        coVerify { positionRepository.updatePosition(position) }
        coVerify { documentRepository.updateDocumentPositions(document.id, listOf(position)) }
        assertEquals(document, documentViewModel.selectedDocument.value)
    }

    @Test
    fun testSelectDocument() {
        val document = Document(id = 1, date = "2023-06-14", symbol = "DOC1", contractors = emptyList(), positions = emptyList())

        documentViewModel.selectDocument(document)

        assertEquals(document, documentViewModel.selectedDocument.value)
    }
}