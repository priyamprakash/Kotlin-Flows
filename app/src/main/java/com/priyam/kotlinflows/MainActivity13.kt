package com.priyam.kotlinflows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 * Mutable shared flow = hot
 */
class MainActivity13 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main13)

        GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
            result.collect {
                Log.d("TAG 13", "onCreate: Item One - $it")
            }

        }
        GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
            delay(2500)
            result.collect {
                Log.d("TAG 13", "onCreate: Item Two- $it")
            }

        }

    }

    private fun producer(): Flow<Int> {
        val mutableSharedFlow = MutableSharedFlow<Int>(replay = 1)

        GlobalScope.launch {
            val list = listOf<Int>(1, 2, 3, 4, 5)
            list.forEach {
                mutableSharedFlow.emit(it)
                delay(1000)

            }
        }
        return mutableSharedFlow
    }
}


//Using this late comer consumer gets "one" previous emitted value