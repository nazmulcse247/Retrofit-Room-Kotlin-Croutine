package com.example.mvvmwithcoroutine.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmwithcoroutine.repo.QuoteRepository

class QuoteViewModelFactory(private val repository: QuoteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuoteViewModel(repository) as T
    }
}