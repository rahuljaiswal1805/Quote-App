package com.example.quotesapp.viewmodelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.quotesapp.repository.QuoteRepository
import com.example.quotesapp.viewmodel.MainViewModel

class MainViewModelFactory(private val quoteRepository: QuoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(quoteRepository) as T
    }

}