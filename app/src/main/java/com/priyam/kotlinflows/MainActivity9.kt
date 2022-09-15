package com.priyam.kotlinflows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

/**
 * This deals with flow on
 *
 * Flow on ke upar sara uss flow on context ka part hoga
 * Remaining : i.e. jinke bad flowon nhi hai vo launch vale context ka part hoga
 */
class MainActivity9 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)
        GlobalScope.launch(Dispatchers.Main) {
            producer()
                .flowOn(Dispatchers.IO)
                .collect {
                    delay(1500)
                    Log.d("Thread: collect", "Collector thread  = ${Thread.currentThread().name}")
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