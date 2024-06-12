package com.example.przyjeciamagazyn.Documents.data.repository

import android.content.Context
import com.example.przyjeciamagazyn.Core.data.ROOM.AppDatabase
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.DocumentDao
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.DocumentPositionDao
import com.example.przyjeciamagazyn.Documents.data.model.Document
import com.example.przyjeciamagazyn.Documents.data.model.DocumentPosition
import kotlinx.coroutines.flow.Flow


class DocumentRepository(context: Context): DocumentDao, DocumentPositionDao {
    private val receipt = AppDatabase.getInstance(context).documentDao()
    private val receiptPosition = AppDatabase.getInstance(context).documentPositionDao()

    override fun getAllDocuments(): Flow<List<Document>>  {
        return receipt.getAllDocuments()
    }

    override fun getPositionsForDocument(receiptId: Int): Flow<List<DocumentPosition>> {
        return receiptPosition.getPositionsForDocument(receiptId)
    }

    override suspend fun insertDocument(receiptDocument: Document) {
        receipt.insertDocument(receiptDocument)
    }

    override suspend fun insertPosition(position: DocumentPosition) {
        receiptPosition.insertPosition(position)
    }

    override suspend fun updatePosition(position: DocumentPosition) {
        receiptPosition.updatePosition(position)
    }

    override fun getPosition(id: Int): Flow<DocumentPosition> {
        return receiptPosition.getPosition(id)
    }

    override suspend fun deleteAllDocuments() {
        receipt.deleteAllDocuments()
    }

    override suspend fun updateDocumentPositions(id: Int, positions: List<DocumentPosition>) {
        receipt.updateDocumentPositions(id, positions)
    }

    override suspend fun updateDocument(updatedReceipt: Document) {
        receipt.updateDocument(updatedReceipt)
    }

    override fun getDocument(id: Int): Flow<Document> {
        return receipt.getDocument(id)
    }

    override fun getDocumentsContainingContractor(contractorId: Int): Flow<List<Document>> {
        return receipt.getDocumentsContainingContractor(contractorId)
    }
}