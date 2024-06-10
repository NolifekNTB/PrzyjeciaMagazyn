package com.example.przyjeciamagazyn.Core.data.ROOM

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.przyjeciamagazyn.Contractors.data.model.Contractor
import com.example.przyjeciamagazyn.Core.data.ROOM.Converter.ContractorConverter
import com.example.przyjeciamagazyn.Core.data.ROOM.Converter.ReceiptPositionConverter
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.ContractorDao
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.ReceiptDao
import com.example.przyjeciamagazyn.Core.data.ROOM.dao.ReceiptPositionDao
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptDocument
import com.example.przyjeciamagazyn.Receipts.data.model.ReceiptPosition

@Database(entities = [
    ReceiptDocument::class,
    ReceiptPosition::class,
    Contractor::class],
    version = 1)
@TypeConverters(ReceiptPositionConverter::class, ContractorConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun receiptDao(): ReceiptDao
    abstract fun receiptPositionDao(): ReceiptPositionDao
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
