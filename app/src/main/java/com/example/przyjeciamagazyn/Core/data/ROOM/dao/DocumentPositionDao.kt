package com.example.przyjeciamagazyn.Core.data.ROOM.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.przyjeciamagazyn.Documents.data.model.DocumentPosition
import kotlinx.coroutines.flow.Flow

@Dao
interface DocumentPositionDao {
    @Query("SELECT * FROM receipt_positions WHERE receiptId = :receiptId")
    fun getPositionsForReceipt(receiptId: Int): Flow<List<DocumentPosition>>

    @Insert
    suspend fun insertReceiptPosition(position: DocumentPosition)

    @Update
    suspend fun updateReceiptPosition(position: DocumentPosition)

    @Query("SELECT * FROM receipt_positions WHERE id = :id")
    fun getPosition(id: Int): Flow<DocumentPosition>
}