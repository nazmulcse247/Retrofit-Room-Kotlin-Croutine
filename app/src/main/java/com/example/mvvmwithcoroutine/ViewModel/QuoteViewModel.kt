package com.example.mvvmwithcoroutine.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmwithcoroutine.model.QuoteResponse
import com.example.mvvmwithcoroutine.repo.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel(){

    val quoteLiveData : LiveData<QuoteResponse>
        get() = repository.quoteLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuote(1)
        }

    }



}