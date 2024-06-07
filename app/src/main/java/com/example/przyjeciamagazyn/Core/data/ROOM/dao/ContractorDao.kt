package com.example.przyjeciamagazyn.Core.data.ROOM.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import kotlinx.coroutines.flow.Flow

@Dao
interface ContractorDao {
    @Query("SELECT * FROM contractors")
    fun getAllContractors(): Flow<List<Contractor>>

    @Insert
    fun insertContractor(contractor: Contractor)
}