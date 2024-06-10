package com.example.przyjeciamagazyn.Core.data.ROOM.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
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
}