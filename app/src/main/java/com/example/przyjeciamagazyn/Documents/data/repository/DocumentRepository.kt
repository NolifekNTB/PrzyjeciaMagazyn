package com.example.przyjeciamagazyn.Documents.data.repository

import android.content.Context
import com.example.przyjeciamagazyn.Core.data.ROOM.AppDatabase
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.DocumentDao
import com.example.przyjeciamagazyn.Documents.data.model.Document
import com.example.przyjeciamagazyn.Documents.data.model.Position
import kotlinx.coroutines.flow.Flow


class DocumentRepository(context: Context): DocumentDao {
    private val document = AppDatabase.getInstance(context).documentDao()

    override fun getAllDocuments(): Flow<List<Document>>  {
        return document.getAllDocuments()
    }

    override fun getDocument(id: Int): Flow<Document> {
        return document.getDocument(id)
    }

    override fun getDocumentsContainingContractor(contractorId: Int): Flow<List<Document>> {
        return document.getDocumentsContainingContractor(contractorId)
    }

    override suspend fun insertDocument(document: Document) {
        this.document.insertDocument(document)
    }

    override suspend fun updateDocument(updatedDocument: Document) {
        document.updateDocument(updatedDocument)
    }

    override suspend fun updateDocumentPositions(id: Int, positions: List<Position>) {
        document.updateDocumentPositions(id, positions)
    }

    override suspend fun deleteAllDocuments() {
        document.deleteAllDocuments()
    }
}