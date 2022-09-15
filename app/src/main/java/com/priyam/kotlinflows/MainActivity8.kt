package com.priyam.kotlinflows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class MainActivity8 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)
        GlobalScope.launch(Dispatchers.Main) {
            val time = measureTimeMillis {
                producer()
                    .collect {
                        delay(1500)
                        Log.d("onCreate: collect", it.toString())
                    }
            }

            Log.d("onCreate: 2500*5 = ", time.toString())

        }

    }

    private fun producer(): Flow<Int> {
        return flow<Int> {
            val list = listOf(1, 2, 3, 4, 5)
            list.forEach {
                delay(1000)
                emit(it)
            }
        }
    }
}