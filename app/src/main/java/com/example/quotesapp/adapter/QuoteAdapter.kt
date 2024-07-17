package com.example.quotesapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.quotesapp.R
import com.example.quotesapp.model.Quote
class QuoteAdapter(private val quote : List<Quote>, private val onQuoteClickListener : OnQuoteClickListener)
    : Adapter<QuoteAdapter.QuoteViewHolder>() {
    private var filteredQuotes: List<Quote> = quote
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_layout,parent,false)
        return QuoteViewHolder(view)
    }
    override fun getItemCount(): Int {
        return filteredQuotes.size
    }
    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
//        holder.content.text = quote[position].content
//        holder.author.text = quote[position].author
        val quote = filteredQuotes[position]
        holder.bind(quote, onQuoteClickListener)
    }
    class QuoteViewHolder(itemView: View) : ViewHolder(itemView) {
        var content = itemView.findViewById<TextView>(R.id.content)
        var author = itemView.findViewById<TextView>(R.id.author)

        fun bind(quote : Quote, onQuoteClickListener: OnQuoteClickListener) {
            content.text = quote.content
            author.text = quote.author
            itemView.setOnClickListener {
                onQuoteClickListener.onQuoteClick(quote.author)
            }
        }
    }
}

