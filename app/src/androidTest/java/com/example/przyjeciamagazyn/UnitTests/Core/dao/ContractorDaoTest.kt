package com.example.przyjeciamagazyn.UnitTests.Core.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Core.data.ROOM.AppDatabase
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.ContractorDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContractorDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var contractorDao: ContractorDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()  // Only for testing purposes
            .build()
        contractorDao = database.contractorDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndGetAllContractors() = runBlocking {
        val contractor = Contractor(1, "SYM", "Test Contractor")
        contractorDao.insertContractor(contractor)

        val contractors = contractorDao.getAllContractors().first()
        assertEquals(1, contractors.size)
        assertEquals(contractor, contractors[0])
    }

    @Test
    fun deleteAllContractors() = runBlocking {
        val contractor1 = Contractor(1, "SYM1", "Test Contractor 1")
        val contractor2 = Contractor(2, "SYM2", "Test Contractor 2")
        contractorDao.insertContractor(contractor1)
        contractorDao.insertContractor(contractor2)

        contractorDao.deleteAllContractors()

        val contractors = contractorDao.getAllContractors().first()
        assertTrue(contractors.isEmpty())
    }

    @Test
    fun updateContractor() = runBlocking {
        val contractor = Contractor(1, "SYM", "Test Contractor")
        contractorDao.insertContractor(contractor)

        val updatedContractor = Contractor(1, "SYM", "Updated Contractor")
        contractorDao.updateContractor(updatedContractor)

        val contractors = contractorDao.getAllContractors().first()
        assertEquals(1, contractors.size)
        assertEquals(updatedContractor, contractors[0])
    }
}