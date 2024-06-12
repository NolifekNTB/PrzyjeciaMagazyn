package com.example.przyjeciamagazyn.Core.data.ROOM.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.przyjeciamagazyn.Documents.data.model.Document
import com.example.przyjeciamagazyn.Documents.data.model.DocumentPosition
import kotlinx.coroutines.flow.Flow

@Dao
interface DocumentDao {
    @Query("SELECT * FROM receipts")
    fun getAllReceipts(): Flow<List<Document>>

    @Insert
    suspend fun insertReceipt(receipt: Document)

    @Query("DELETE FROM receipts")
    suspend fun deleteAllReceipts()

    @Query("UPDATE receipts SET positions = :positions WHERE id = :id")
    suspend fun updateReceiptPositions(id: Int, positions: List<DocumentPosition>)

    @Update
    suspend fun updateReceipt(updatedReceipt: Document)

    @Query("SELECT * FROM receipts WHERE id = :id")
    fun getReceipt(id: Int): Flow<Document>

    @Query("SELECT * FROM receipts WHERE :contractorId IN (SELECT id FROM contractors WHERE contractors.id = :contractorId)")
    fun getReceiptsContainingContractor(contractorId: Int): Flow<List<Document>>

}