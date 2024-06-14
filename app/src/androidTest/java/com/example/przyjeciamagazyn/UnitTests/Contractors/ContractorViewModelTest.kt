package com.example.przyjeciamagazyn.UnitTests.Contractors

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.przyjeciamagazyn.Contractors.data.repository.ContractorRepository
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Documents.data.repository.DocumentRepository
import com.example.przyjeciamagazyn.Documents.data.model.Document
import com.example.przyjeciamagazyn.Contractors.presentation.ContractorViewModel
import com.example.przyjeciamagazyn.Documents.data.model.Position
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Test
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class ContractorViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var contractorRepository: ContractorRepository
    private lateinit var documentRepository: DocumentRepository
    private lateinit var contractorViewModel: ContractorViewModel

    @Before
    fun setUp() {
        contractorRepository = mockk()
        documentRepository = mockk()

        coEvery { contractorRepository.getAllContractors() } returns flowOf(emptyList())
        coEvery { contractorRepository.insertContractor(any()) } returns Unit
        coEvery { contractorRepository.updateContractor(any()) } returns Unit
        coEvery { documentRepository.getDocumentsContainingContractor(any()) } returns flowOf(emptyList())
        coEvery { documentRepository.updateDocument(any()) } returns Unit

        contractorViewModel = ContractorViewModel(contractorRepository, documentRepository)
    }

    @Test
    fun testGetAllContractors() = coroutineTestRule.scope.runTest {
        val contractors = listOf(
            Contractor(id = 1, symbol = "SYM1", name = "John Doe"),
            Contractor(id = 2, symbol = "SYM2", name = "Jane Doe")
        )
        coEvery { contractorRepository.getAllContractors() } returns flowOf(contractors)

        contractorViewModel.getAllContractors()
        coroutineTestRule.scope.advanceUntilIdle()

        assertEquals(contractors, contractorViewModel.contractors.value)
    }

    @Test
    fun testInsertContractor() = coroutineTestRule.scope.runTest {
        val contractor = Contractor(id = 1, symbol = "SYM1", name = "John Doe")
        coEvery { contractorRepository.insertContractor(contractor) } returns Unit
        coEvery { contractorRepository.getAllContractors() } returns flowOf(listOf(contractor))

        contractorViewModel.insertContractor(contractor)
        coroutineTestRule.scope.advanceUntilIdle()

        coVerify { contractorRepository.insertContractor(contractor) }
        assertEquals(listOf(contractor), contractorViewModel.contractors.value)
    }

    @Test
    fun testUpdateContractor() = coroutineTestRule.scope.runTest {
        val contractor = Contractor(id = 1, symbol = "SYM1", name = "John Doe Updated")
        val position = Position(id = 1, documentId = 1, productName = "Product A", unit = "kg", quantity = 10)
        val document = Document(
            id = 1,
            date = "2023-06-14",
            symbol = "DOC1",
            contractors = listOf(contractor),
            positions = listOf(position)
        )
        coEvery { contractorRepository.updateContractor(contractor) } returns Unit
        coEvery { documentRepository.getDocumentsContainingContractor(contractor.id) } returns flowOf(listOf(document))
        coEvery { documentRepository.updateDocument(any()) } returns Unit
        coEvery { contractorRepository.getAllContractors() } returns flowOf(listOf(contractor))

        contractorViewModel.updateContractor(contractor)
        coroutineTestRule.scope.advanceUntilIdle()

        coVerify { contractorRepository.updateContractor(contractor) }
        coVerify { documentRepository.updateDocument(any()) }
        assertEquals(listOf(contractor), contractorViewModel.contractors.value)
    }

    @Test
    fun testSelectContractor() {
        val contractor = Contractor(id = 1, symbol = "SYM1", name = "John Doe")

        contractorViewModel.selectContractor(contractor)

        assertEquals(contractor, contractorViewModel.selectedContractor.value)
    }
}