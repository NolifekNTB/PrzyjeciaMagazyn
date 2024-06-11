package com.example.przyjeciamagazyn.Contractors.data.repository

import android.content.Context
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Core.data.ROOM.AppDatabase
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.ContractorDao
import kotlinx.coroutines.flow.Flow

class ContractorRepository(context: Context): ContractorDao {
    private val contractorDao = AppDatabase.getInstance(context).contractorDao()

    override fun getAllContractors(): Flow<List<Contractor>> {
        return contractorDao.getAllContractors()
    }

    override suspend fun insertContractor(contractor: Contractor) {
        contractorDao.insertContractor(contractor)
    }

    override suspend fun deleteAllContractors() {
        contractorDao.deleteAllContractors()
    }

    override suspend fun updateContractor(contractor: Contractor) {
        contractorDao.updateContractor(contractor)
    }
}