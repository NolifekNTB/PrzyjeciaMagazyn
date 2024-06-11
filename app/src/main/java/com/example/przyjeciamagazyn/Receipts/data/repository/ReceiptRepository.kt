package com.example.przyjeciamagazyn.Receipts.data.repository

import android.content.Context
import com.example.przyjeciamagazyn.Core.data.ROOM.AppDatabase
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.ReceiptDao
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.ReceiptPositionDao
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptDocument
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptPosition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


class ReceiptRepository(context: Context): ReceiptDao, ReceiptPositionDao {
    private val receipt = AppDatabase.getInstance(context).receiptDao()
    private val receiptPosition = AppDatabase.getInstance(context).receiptPositionDao()

    override fun getAllReceipts(): Flow<List<ReceiptDocument>>  {
        return receipt.getAllReceipts()
    }

    override fun getPositionsForReceipt(receiptId: Int): Flow<List<ReceiptPosition>> {
        return receiptPosition.getPositionsForReceipt(receiptId)
    }

    override suspend fun insertReceipt(receiptDocument: ReceiptDocument) {
        receipt.insertReceipt(receiptDocument)
    }

    override suspend fun insertReceiptPosition(position: ReceiptPosition) {
        receiptPosition.insertReceiptPosition(position)
    }

    override suspend fun updateReceiptPosition(position: ReceiptPosition) {
        receiptPosition.updateReceiptPosition(position)
    }

    override fun getPosition(id: Int): Flow<ReceiptPosition> {
        return receiptPosition.getPosition(id)
    }

    override suspend fun deleteAllReceipts() {
        receipt.deleteAllReceipts()
    }

    override suspend fun updateReceiptPositions(id: Int, positions: List<ReceiptPosition>) {
        receipt.updateReceiptPositions(id, positions)
    }

    override suspend fun updateReceipt(updatedReceipt: ReceiptDocument) {
        receipt.updateReceipt(updatedReceipt)
    }

    override fun getReceipt(id: Int): Flow<ReceiptDocument> {
        return receipt.getReceipt(id)
    }

    override fun getReceiptsContainingContractor(contractorId: Int): Flow<List<ReceiptDocument>> {
        return receipt.getReceiptsContainingContractor(contractorId)
    }
}