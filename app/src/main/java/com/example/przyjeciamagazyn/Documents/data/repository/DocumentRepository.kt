package com.example.przyjeciamagazyn.Documents.data.repository

import android.content.Context
import com.example.przyjeciamagazyn.Core.data.ROOM.AppDatabase
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.DocumentDao
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.DocumentPositionDao
import com.example.przyjeciamagazyn.Documents.data.model.Document
import com.example.przyjeciamagazyn.Documents.data.model.DocumentPosition
import kotlinx.coroutines.flow.Flow


class DocumentRepository(context: Context): DocumentDao, DocumentPositionDao {
    private val receipt = AppDatabase.getInstance(context).receiptDao()
    private val receiptPosition = AppDatabase.getInstance(context).receiptPositionDao()

    override fun getAllReceipts(): Flow<List<Document>>  {
        return receipt.getAllReceipts()
    }

    override fun getPositionsForReceipt(receiptId: Int): Flow<List<DocumentPosition>> {
        return receiptPosition.getPositionsForReceipt(receiptId)
    }

    override suspend fun insertReceipt(receiptDocument: Document) {
        receipt.insertReceipt(receiptDocument)
    }

    override suspend fun insertReceiptPosition(position: DocumentPosition) {
        receiptPosition.insertReceiptPosition(position)
    }

    override suspend fun updateReceiptPosition(position: DocumentPosition) {
        receiptPosition.updateReceiptPosition(position)
    }

    override fun getPosition(id: Int): Flow<DocumentPosition> {
        return receiptPosition.getPosition(id)
    }

    override suspend fun deleteAllReceipts() {
        receipt.deleteAllReceipts()
    }

    override suspend fun updateReceiptPositions(id: Int, positions: List<DocumentPosition>) {
        receipt.updateReceiptPositions(id, positions)
    }

    override suspend fun updateReceipt(updatedReceipt: Document) {
        receipt.updateReceipt(updatedReceipt)
    }

    override fun getReceipt(id: Int): Flow<Document> {
        return receipt.getReceipt(id)
    }

    override fun getReceiptsContainingContractor(contractorId: Int): Flow<List<Document>> {
        return receipt.getReceiptsContainingContractor(contractorId)
    }
}