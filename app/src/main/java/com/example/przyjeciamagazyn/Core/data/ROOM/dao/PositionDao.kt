package com.example.przyjeciamagazyn.Core.data.ROOM.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.przyjeciamagazyn.Documents.data.model.Position
import kotlinx.coroutines.flow.Flow

@Dao
interface PositionDao {
    @Query("SELECT * FROM positions WHERE id = :id")
    fun getPosition(id: Int): Flow<Position>

    @Query("SELECT * FROM positions WHERE documentId = :documentId")
    fun getPositionsForDocument(documentId: Int): Flow<List<Position>>

    @Insert
    suspend fun insertPosition(position: Position)

    @Update
    suspend fun updatePosition(position: Position)
}