package com.example.quotesapp

import android.app.Application
import com.example.quotesapp.api.QuotesAPI
import com.example.quotesapp.api.RetrofitHelper
import com.example.quotesapp.database.QuoteDatabase
import com.example.quotesapp.repository.QuoteRepository

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteDatabase = QuoteDatabase.getDatabase(applicationContext)
        val quotesAPI = RetrofitHelper.getInstance().create(QuotesAPI::class.java)
        quoteRepository = QuoteRepository(quoteDatabase, quotesAPI, applicationContext)
    }
}