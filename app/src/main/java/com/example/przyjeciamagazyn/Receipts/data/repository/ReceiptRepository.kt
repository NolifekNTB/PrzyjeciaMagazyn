package com.example.przyjeciamagazyn.Receipts.data.repository

import android.content.Context
import com.example.przyjeciamagazyn.Core.data.ROOM.AppDatabase
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.ReceiptDao
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.ReceiptPositionDao
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptDocument
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptPosition
import kotlinx.coroutines.flow.Flow


class ReceiptRepository(context: Context): ReceiptDao, ReceiptPositionDao {
    private val receipt = AppDatabase.getInstance(context).receiptDao()
    private val receiptPosition = AppDatabase.getInstance(context).receiptPositionDao()

    override fun getAllReceipts(): Flow<List<ReceiptDocument>> {
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
}