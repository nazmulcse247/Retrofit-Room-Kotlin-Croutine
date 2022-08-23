package com.example.mvvmwithcoroutine.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmwithcoroutine.api.QuoteService
import com.example.mvvmwithcoroutine.db.QuoteDatabase
import com.example.mvvmwithcoroutine.model.QuoteResponse
import com.example.mvvmwithcoroutine.utils.NetworksUtils

class QuoteRepository(

    private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val context: Context

    ) {

    private val quoteMutableLiveData = MutableLiveData<QuoteResponse>()

    val quoteLiveData : LiveData<QuoteResponse>
    get() = quoteMutableLiveData

    suspend fun getQuote(page : Int) {
        if (NetworksUtils.isInternetAvailable(context)){
            val response = quoteService.getQuotes(page)
            if (response.body() != null){
                quoteDatabase.quoteDao().insertQuoteList(response.body()!!.results)
                quoteMutableLiveData.postValue(response.body())
            }
        }
        else {
            val quotes = quoteDatabase.quoteDao().getQuoteList()
            val quotesList = QuoteResponse(1,1,1,quotes,1,1)
            quoteMutableLiveData.postValue(quotesList)
        }
    }
}