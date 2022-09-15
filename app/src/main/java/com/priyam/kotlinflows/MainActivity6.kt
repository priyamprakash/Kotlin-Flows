package com.priyam.kotlinflows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)
        GlobalScope.launch(Dispatchers.Main) {
            producer()
                .onStart {
                    emit(-1)
                    Log.d("onCreate: onStart", "Starting Out")
                }
                .onCompletion {
                    emit(6)
                    Log.d("onCreate: onCompletion", "Completed")
                }
                .onEach {
                    Log.d("onCreate: onEach", "About to emit - ${it.toString()}")
                }
                .collect {
                    Log.d("onCreate: collect", "${it.toString()}")
                }
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