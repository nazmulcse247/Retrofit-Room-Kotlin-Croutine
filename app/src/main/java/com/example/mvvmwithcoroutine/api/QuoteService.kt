package com.example.mvvmwithcoroutine.api

import com.example.mvvmwithcoroutine.model.QuoteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {

    @GET("quotes")
    suspend fun getQuotes(
        @Query("page") page : Int
    ) : Response<QuoteResponse>

}