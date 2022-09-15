package com.priyam.kotlinflows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        GlobalScope.launch {
            val data: Flow<Int> = producer()
            data.collect {
                Log.d("consumer: 1", it.toString())
            }
        }

        GlobalScope.launch {
            val data: Flow<Int> = producer()
            delay(2500)   //can be commented for ideal case
            data.collect {
                Log.d("consumer: 2", it.toString())
            }
        }
    }

    fun producer() = flow<Int> {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        list.forEach {
            delay(1000)
            Log.d("producer: ", it.toString())
            emit(it)
        }
    }
}