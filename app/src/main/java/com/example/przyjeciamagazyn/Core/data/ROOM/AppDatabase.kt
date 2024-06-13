package com.example.przyjeciamagazyn.Core.data.ROOM

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Core.data.ROOM.converters.ContractorConverter
import com.example.przyjeciamagazyn.Core.data.ROOM.converters.DocumentPositionConverter
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.ContractorDao
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.DocumentDao
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.PositionDao
import com.example.przyjeciamagazyn.Documents.data.model.Document
import com.example.przyjeciamagazyn.Documents.data.model.Position

@Database(
    entities = [
        Document::class,
        Position::class,
        Contractor::class
               ],
    version = 1
)
@TypeConverters(DocumentPositionConverter::class, ContractorConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun documentDao(): DocumentDao
    abstract fun documentPositionDao(): PositionDao
    abstract fun contractorDao(): ContractorDao


    companion object {
        private var db: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (db == null) {
                db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "przyjeciamagazyn.db"
                ).build()
            }

            return db!!
        }
    }
}
