package com.example.mvvmwithcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmwithcoroutine.ViewModel.QuoteViewModel
import com.example.mvvmwithcoroutine.ViewModel.QuoteViewModelFactory
import com.example.mvvmwithcoroutine.adapter.QuotesAdapter
import com.example.mvvmwithcoroutine.api.QuoteService
import com.example.mvvmwithcoroutine.api.RetrofitHelper
import com.example.mvvmwithcoroutine.db.QuoteDatabase
import com.example.mvvmwithcoroutine.repo.QuoteRepository

class MainActivity : AppCompatActivity() {

    lateinit var quoteViewModel: QuoteViewModel
    lateinit var quotesAdapter: QuotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val quoteRv = findViewById<RecyclerView>(R.id.quoteRv)

        val quoteService = RetrofitHelper.getRetrofit().create(QuoteService::class.java)

        val quoteDatabase = QuoteDatabase.getQuoteDatabase(this)
        val quoteRepository = QuoteRepository(quoteService,quoteDatabase,this)

        quoteViewModel = ViewModelProvider(this,QuoteViewModelFactory(quoteRepository)).get(QuoteViewModel::class.java)

        quoteViewModel.quoteLiveData.observe(this, Observer { QuoteResponse ->
            Log.d("mvvm", QuoteResponse.results.size.toString())
//            Toast.makeText(this,QuoteResponse.results.toString(),Toast.LENGTH_LONG).show()

            quotesAdapter = QuotesAdapter()
            quotesAdapter.submitList(QuoteResponse.results)
            quoteRv.layoutManager = LinearLayoutManager(this)
            quoteRv.adapter = quotesAdapter

        })
    }
}