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
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.DocumentPositionDao
import com.example.przyjeciamagazyn.Documents.data.model.Document
import com.example.przyjeciamagazyn.Documents.data.model.DocumentPosition

@Database(entities = [
    Document::class,
    DocumentPosition::class,
    Contractor::class],
    version = 1)
@TypeConverters(DocumentPositionConverter::class, ContractorConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun receiptDao(): DocumentDao
    abstract fun receiptPositionDao(): DocumentPositionDao
    abstract fun contractorDao(): ContractorDao


    companion object {
        private var db: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (db == null) {
                db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "anime-database.db"
                )
                    .build()
            }

            return db!!
        }
    }
}
