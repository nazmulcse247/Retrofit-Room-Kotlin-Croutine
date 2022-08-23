package com.example.mvvmwithcoroutine.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmwithcoroutine.model.Result


@Database(
    entities = [Result::class],
    version = 1
)
abstract class QuoteDatabase : RoomDatabase(){

    abstract fun quoteDao() : QuoteDao

    companion object {

        @Volatile
        private var INSTANCE : QuoteDatabase? = null

        fun getQuoteDatabase(context : Context) : QuoteDatabase {

            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room
                        .databaseBuilder(context,QuoteDatabase::class.java,"quoteDB")
                        .build()
                }
            }
            return INSTANCE!!

        }

    }
}