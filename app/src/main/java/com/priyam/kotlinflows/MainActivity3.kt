package com.priyam.kotlinflows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val job = GlobalScope.launch {
            val data: Flow<Int> = producer()
            data.collect{
                Log.d( "consumer: " , it.toString())
            }
        }

        GlobalScope.launch {
            delay(3500)
            job.cancel()
        }

    }

    fun producer() = flow<Int>{
        val list = listOf(1, 2, 3,4,5,6,7,8,9,10)
        list.forEach{
            delay(1000)
            Log.d("producer: " , it.toString())
            emit(it)
        }
    }
}