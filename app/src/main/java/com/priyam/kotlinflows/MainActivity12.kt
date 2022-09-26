package com.priyam.kotlinflows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 * Implementing .catch function of producer method
 */
class MainActivity12 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main12)

        GlobalScope.launch(Dispatchers.Main) {
            try{
                producer()
                    .collect{
                        Log.d("TAG 12", "onCreate: Collector Thread - $it ${Thread.currentThread().name}")
                    }
            }
            catch (e: Exception){
                Log.d("TAG 12",  e.message.toString())
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
                throw  Exception("Error in emitter")
            }
        }.catch {
            Log.d("Thread: producer", "Emitter thread  = ${it.message}")
            emit(-1)
        }
    }
}