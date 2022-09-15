package com.priyam.kotlinflows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Flow on - with filters
 *
 * <code 1>
 *     .flowOn(Thread 1)
 *
 * <code 2>
 *     .flowOn(Thread 2)
 */
class MainActivity10 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main10)
        GlobalScope.launch(Dispatchers.Main) {
            producer()
                .map{
                    delay(1000)
                    it * 2
                    Log.d("onCreate:  Map ", "Map Thread=  ${Thread.currentThread().name}")
                }
                .flowOn(Dispatchers.IO)
                .filter {
                    delay(500)
                    Log.d("onCreate: Filter", "Filter Thread = ${Thread.currentThread().name}")
                    it < 8

                }
                .flowOn(Dispatchers.Main)
                .collect {
                    Log.d("onCreate: collect", "Collector thread  = ${Thread.currentThread().name}")
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
            }
        }
    }
}