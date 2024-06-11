package com.example.przyjeciamagazyn.Core.data.ROOM.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptDocument
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptPosition
import kotlinx.coroutines.flow.Flow

@Dao
interface ReceiptDao {
    @Query("SELECT * FROM receipts")
    fun getAllReceipts(): Flow<List<ReceiptDocument>>

    @Insert
    suspend fun insertReceipt(receipt: ReceiptDocument)

    @Query("DELETE FROM receipts")
    suspend fun deleteAllReceipts()

    @Query("UPDATE receipts SET positions = :positions WHERE id = :id")
    suspend fun updateReceiptPositions(id: Int, positions: List<ReceiptPosition>)

    @Update
    suspend fun updateReceipt(updatedReceipt: ReceiptDocument)

    @Query("SELECT * FROM receipts WHERE id = :id")
    fun getReceipt(id: Int): Flow<ReceiptDocument>

    @Query("SELECT * FROM receipts WHERE :contractorId IN (SELECT id FROM contractors WHERE contractors.id = :contractorId)")
    fun getReceiptsContainingContractor(contractorId: Int): Flow<List<ReceiptDocument>>

}