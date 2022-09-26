package com.priyam.kotlinflows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


class MainActivity11 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main11)

        GlobalScope.launch(Dispatchers.Main) {
           try{
               producer()
                   .collect{
                       Log.d("TAG 11", "onCreate: Collector Thread - ${Thread.currentThread().name}")
                   }
                   throw  Exception("Error in collector")

           }
           catch (e: Exception){
               Log.d("TAG 11",  e.message.toString())
           }

        }

    }

    private fun producer(): Flow<Int> {
        return flow<Int> {
            val list = listOf(1, 2, 3, 4, 5)
            list.forEach {
                delay(1000)
                Log.d("Thread: producer", "Emitter thread  = ${Thread.currentThread().name}")
                emit(it)
//                throw  Exception("Error in Emitter")
            }
        }
    }
}