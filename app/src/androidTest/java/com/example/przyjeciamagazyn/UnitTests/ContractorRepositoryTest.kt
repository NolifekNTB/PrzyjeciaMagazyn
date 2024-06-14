package com.example.przyjeciamagazyn.UnitTests

import android.content.Context
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Contractors.data.repository.ContractorRepository
import com.example.przyjeciamagazyn.Core.data.ROOM.AppDatabase
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.ContractorDao
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import io.mockk.*


class ContractorRepositoryTest {

    private lateinit var contractorDao: ContractorDao
    private lateinit var repository: ContractorRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        contractorDao = mockk()
        val context = mockk<Context>()

        every { context.getSystemService(any()) } returns null

        mockkStatic(AppDatabase::class)
        every { AppDatabase.getInstance(context).contractorDao() } returns contractorDao

        repository = ContractorRepository(context).apply {
            contractorDao = this@ContractorRepositoryTest.contractorDao
        }
    }

    @Test
    fun `getAllContractors returns flow from DAO`(): Unit = runBlocking {
        // Arrange
        val contractors = listOf(Contractor(1, "SYM", "Test Contractor"))
        coEvery { contractorDao.getAllContractors() } returns flowOf(contractors)

        // Act
        val result = repository.getAllContractors().first()

        // Assert
        assertEquals(contractors, result)
        coVerify { contractorDao.getAllContractors() }
    }

    @Test
    fun `insertContractor calls DAO`() = runBlocking {
        // Arrange
        val contractor = Contractor(1, "SYM", "Test Contractor")
        coEvery { contractorDao.insertContractor(contractor) } just Runs

        // Act
        repository.insertContractor(contractor)

        // Assert
        coVerify { contractorDao.insertContractor(contractor) }
    }

    @Test
    fun `deleteAllContractors calls DAO`() = runBlocking {
        coEvery { contractorDao.deleteAllContractors() } just Runs
        // Act
        repository.deleteAllContractors()

        // Assert
        coVerify { contractorDao.deleteAllContractors() }
    }

    @Test
    fun `updateContractor calls DAO`() = runBlocking {
        // Arrange
        val contractor = Contractor(1, "SYM", "Test Contractor")
        coEvery { contractorDao.updateContractor(contractor) } just Runs

        // Act
        repository.updateContractor(contractor)

        // Assert
        coVerify { contractorDao.updateContractor(contractor) }
    }
}