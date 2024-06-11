package com.example.przyjeciamagazyn.Core.data.ROOM.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptPosition
import kotlinx.coroutines.flow.Flow

@Dao
interface ReceiptPositionDao {
    @Query("SELECT * FROM receipt_positions WHERE receiptId = :receiptId")
    fun getPositionsForReceipt(receiptId: Int): Flow<List<ReceiptPosition>>

    @Insert
    suspend fun insertReceiptPosition(position: ReceiptPosition)

    @Update
    suspend fun updateReceiptPosition(position: ReceiptPosition)

    @Query("SELECT * FROM receipt_positions WHERE id = :id")
    fun getPosition(id: Int): Flow<ReceiptPosition>
}