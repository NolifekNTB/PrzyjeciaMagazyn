package com.example.przyjeciamagazyn.Core.data.ROOM.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptDocument
import kotlinx.coroutines.flow.Flow

@Dao
interface ReceiptDao {
    @Query("SELECT * FROM receipts")
    fun getAllReceipts(): Flow<List<ReceiptDocument>>

    @Insert
    suspend fun insertReceipt(receipt: ReceiptDocument)
}