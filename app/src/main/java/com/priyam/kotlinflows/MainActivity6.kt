package com.priyam.kotlinflows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/*** first, toList are terminal operators. Therefore, they are suspend functions
 */
class MainActivity6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)
        GlobalScope.launch(Dispatchers.Main) {
            val result1 = producer().first()
            Log.d("onCreate: result1 first" , result1.toString())
            val result2 = producer().toList()
            Log.d("onCreate: result2 list" , result2.toString())

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