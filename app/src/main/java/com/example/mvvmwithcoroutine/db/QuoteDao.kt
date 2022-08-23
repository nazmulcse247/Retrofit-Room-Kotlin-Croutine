package com.example.mvvmwithcoroutine.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmwithcoroutine.model.Result

@Dao
interface QuoteDao {

    @Insert
    suspend fun insertQuoteList(quoteList: List<Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuoteList() : List<Result>
}