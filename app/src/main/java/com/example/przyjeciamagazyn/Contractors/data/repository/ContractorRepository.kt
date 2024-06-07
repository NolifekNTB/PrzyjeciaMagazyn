package com.example.przyjeciamagazyn.Contractors.data.repository

import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.ContractorDao
import kotlinx.coroutines.flow.Flow

class ContractorRepository(private val contractorDao: ContractorDao) {

    fun getAllContractors(): Flow<List<Contractor>> {
        return contractorDao.getAllContractors()
    }

    suspend fun insertContractor(contractor: Contractor) {
        contractorDao.insertContractor(contractor)
    }
}