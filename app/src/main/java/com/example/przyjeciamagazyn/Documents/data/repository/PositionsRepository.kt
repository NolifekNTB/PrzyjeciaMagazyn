package com.example.przyjeciamagazyn.Documents.data.repository

import android.content.Context
import com.example.przyjeciamagazyn.Core.data.ROOM.AppDatabase
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.PositionDao
import com.example.przyjeciamagazyn.Documents.data.model.Position
import kotlinx.coroutines.flow.Flow

class PositionsRepository(context: Context): PositionDao {
    var documentPosition = AppDatabase.getInstance(context).documentPositionDao()

    override fun getPosition(id: Int): Flow<Position> {
        return documentPosition.getPosition(id)
    }

    override fun getPositionsForDocument(documentId: Int): Flow<List<Position>> {
        return documentPosition.getPositionsForDocument(documentId)
    }

    override suspend fun insertPosition(position: Position) {
        documentPosition.insertPosition(position)
    }

    override suspend fun updatePosition(position: Position) {
        documentPosition.updatePosition(position)
    }
}