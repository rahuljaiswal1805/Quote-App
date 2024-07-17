package com.example.quotesapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.model.Quote
import com.example.quotesapp.model.QuoteList
import com.example.quotesapp.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MainViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    var filteredQuotes = MutableLiveData<QuoteList>()
//        get() = quoteRepository.quotes

    val quotes : LiveData<QuoteList>
        get() = quoteRepository.quotes

    init {
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.getQuote(1)
        }

//         Initialize filtered quotes with all quotes initially
//        filteredQuotes.value = quoteRepository.quotesLiveData.value
    }



    // Function to filter quotes based on query
    fun filter(query: String) {

        if (query.isEmpty()) {
            // If query is empty, show all quotes
//            viewModelScope.launch(Dispatchers.IO) {
//                quoteRepository.getQuote(1)
//            }
            filteredQuotes.value = quotes.value
        } else {
            // Filter quotes based on query and update LiveData

            val filteredList = quoteRepository.quotes.value?.results?.filter {
                it.author.contains(query, ignoreCase = true) || it.content.contains(query, ignoreCase = true)
            }
            filteredQuotes.value = filteredList?.let { QuoteList(0, 1, 1,it, 1, 1) }
        }
    }


//
//    fun filter(query: String) {
//        filteredQuotes = if (query.isEmpty()) {
//            filteredQuotes
//        } else {
//            filteredQuotes.filter { it.author.contains(query, ignoreCase = true) || it.content.contains(query, ignoreCase = true) }
//        }
//    }



//    fun getQuote() : LiveData<List<Quote>> {
//        return quoteRepository.getQuote()
//    }

//    fun insertQuote(quote : Quote) {
//        viewModelScope.launch(Dispatchers.IO) {
//            quoteRepository.insertQuote(quote)
//        }
//
//    }
}