package com.example.przyjeciamagazyn.Core.data.ROOM.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.przyjeciamagazyn.Documents.data.model.Document
import com.example.przyjeciamagazyn.Documents.data.model.Position
import kotlinx.coroutines.flow.Flow

@Dao
interface DocumentDao {
    @Query("SELECT * FROM documents")
    fun getAllDocuments(): Flow<List<Document>>

    @Query("SELECT * FROM documents WHERE id = :id")
    fun getDocument(id: Int): Flow<Document>

    @Query("SELECT * FROM documents WHERE :contractorId IN (SELECT id FROM contractors WHERE contractors.id = :contractorId)")
    fun getDocumentsContainingContractor(contractorId: Int): Flow<List<Document>>

    @Insert
    suspend fun insertDocument(document: Document)

    @Query("UPDATE documents SET positions = :positions WHERE id = :id")
    suspend fun updateDocumentPositions(id: Int, positions: List<Position>)

    @Update
    suspend fun updateDocument(updatedDocument: Document)

    @Query("DELETE FROM documents")
    suspend fun deleteAllDocuments()
}