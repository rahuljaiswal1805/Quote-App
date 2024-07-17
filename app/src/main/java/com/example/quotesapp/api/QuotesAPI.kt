package com.example.quotesapp.api

import com.example.quotesapp.model.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface QuotesAPI {

    @GET("/quotes")
    suspend fun getQuote(@Query("page") page : Int) : Response<QuoteList>

//    baseUrl + @Get + ?page=1
}