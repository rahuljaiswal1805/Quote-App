package com.example.quotesapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quotesapp.model.Quote

@Dao
interface QuoteDao {

    @Insert
    suspend fun insertQuote(quote : List<Quote>)

    @Query("SELECT * FROM quote")
    suspend fun getQuote() : List<Quote>
}