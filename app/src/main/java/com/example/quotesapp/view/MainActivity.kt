package com.example.quotesapp.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotesapp.QuoteApplication
import com.example.quotesapp.adapter.OnQuoteClickListener
import com.example.quotesapp.adapter.QuoteAdapter
import com.example.quotesapp.viewmodel.MainViewModel
import com.example.quotesapp.viewmodelFactory.MainViewModelFactory
import com.example.quotesapp.R
import com.example.quotesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnQuoteClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.quoteList)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        val quoteDatabase = QuoteDatabase.getDatabase(this.applicationContext)
//        val quotesAPI = RetrofitHelper.getInstance().create(QuotesAPI::class.java)
//        val quoteRepository = QuoteRepository(quoteDatabase, quotesAPI)
//
//        binding.searchView.queryHint = "Search content..."
//        binding.searchView.setIconifiedByDefault(false)
//        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                if(newText.toString().isEmpty()) {
//
//                }
//                else {
//
//                }
//                return false
//            }
//
//        })

        binding.searchView.queryHint = "Search content..."
        binding.searchView.setIconifiedByDefault(false)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mainViewModel.filter(newText ?: "")
                return true
            }
        })


        val quoteRepository = (application as QuoteApplication).quoteRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(quoteRepository)).get(MainViewModel::class.java)

//        adapter = QuoteAdapter(listOf(), this)
//        binding.quoteList.adapter = adapter
//        binding.quoteList.layoutManager = LinearLayoutManager(this)

        mainViewModel.quotes.observe(this, Observer {
//            binding.quotes = it.toString()
            binding.quoteList.adapter = QuoteAdapter(it.results,this)
            binding.quoteList.layoutManager = LinearLayoutManager(this)
        })

        mainViewModel.filteredQuotes.observe(this, Observer {
//            binding.quotes = it.toString()
            binding.quoteList.adapter = QuoteAdapter(it.results,this)
            binding.quoteList.layoutManager = LinearLayoutManager(this)
        })

//        binding.btnAddQuote.setOnClickListener {
//            val quote = Quote(0,"I am a winner","Rahul")
//            mainViewModel.insertQuote(quote)
//        }

        mainViewModel.quotes.observe(this, Observer {
            Log.d("Rahul", it.results.toString())
            Toast.makeText(this,it.results.size.toString(),Toast.LENGTH_LONG).show()
        })
    }

    override fun onQuoteClick(author: String) {
        Toast.makeText(this,author,Toast.LENGTH_LONG).show()
    }
}