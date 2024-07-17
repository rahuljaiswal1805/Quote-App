package com.example.quotesapp.repository

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quotesapp.api.QuotesAPI
import com.example.quotesapp.database.QuoteDatabase
import com.example.quotesapp.model.QuoteList
import com.example.quotesapp.utils.NetworkUtils

class QuoteRepository(
    private val quoteDatabase: QuoteDatabase,
    private val quotesAPI: QuotesAPI,
    private val applicationContext: Context
) {



    private val quotesLiveData = MutableLiveData<QuoteList>()

    val quotes : LiveData<QuoteList>
        get() = quotesLiveData

    suspend fun getQuote(page : Int) {

        if(NetworkUtils.isOnline(applicationContext)) {
            val result = quotesAPI.getQuote(page)
            if(result?.body() != null) {
                quoteDatabase.getQuoteDao().insertQuote(result.body()!!.results)
                quotesLiveData.postValue(result.body())
            }
        }
        else {
            val quote = quoteDatabase.getQuoteDao().getQuote()
            val quoteList = QuoteList(1,1,1,quote,1,1)
            quotesLiveData.postValue(quoteList)
        }


    }





//    suspend fun insertQuote(quote : Quote) {
//        quoteDao.insertQuote(quote)
//    }
//
//    fun getQuote() : LiveData<List<Quote>> {
//        return quoteDao.getQuote()
//    }


}