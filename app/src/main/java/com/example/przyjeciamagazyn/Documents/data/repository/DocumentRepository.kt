package com.example.przyjeciamagazyn.Documents.data.repository

import android.content.Context
import com.example.przyjeciamagazyn.Core.data.ROOM.AppDatabase
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.DocumentDao
import com.example.przyjeciamagazyn.Documents.data.model.Document
import com.example.przyjeciamagazyn.Documents.data.model.Position
import kotlinx.coroutines.flow.Flow


class DocumentRepository(context: Context): DocumentDao {
    private val receipt = AppDatabase.getInstance(context).documentDao()

    override fun getAllDocuments(): Flow<List<Document>>  {
        return receipt.getAllDocuments()
    }

    override fun getDocument(id: Int): Flow<Document> {
        return receipt.getDocument(id)
    }

    override fun getDocumentsContainingContractor(contractorId: Int): Flow<List<Document>> {
        return receipt.getDocumentsContainingContractor(contractorId)
    }

    override suspend fun insertDocument(document: Document) {
        receipt.insertDocument(document)
    }

    override suspend fun updateDocument(updatedDocument: Document) {
        receipt.updateDocument(updatedDocument)
    }

    override suspend fun updateDocumentPositions(id: Int, positions: List<Position>) {
        receipt.updateDocumentPositions(id, positions)
    }

    override suspend fun deleteAllDocuments() {
        receipt.deleteAllDocuments()
    }
}