package com.example.quotesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quotesapp.model.Quote

@Database(entities = [Quote::class], version = 1)

abstract class QuoteDatabase : RoomDatabase() {

    abstract fun getQuoteDao() : QuoteDao

    companion object {
//        @Volatile
        private var INSTANCE : QuoteDatabase? = null

        fun getDatabase(context: Context) : QuoteDatabase {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    QuoteDatabase::class.java,
                    "quote_database"
                )
//                    .createFromAsset("quotes.db") //Prepopulate the db
                    .build()
            }
            return INSTANCE!!
        }




    }
}