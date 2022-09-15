package com.priyam.kotlinflows

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        Consumer
        CoroutineScope(Dispatchers.Main).launch {
            getUserNames().forEach{
                Log.d("onCreate: Flows", it)
            }
        }
    }

    private suspend fun getUserNames(): List<String>{
//        Producer
        val list = mutableListOf<String>()
        list.add(getUser(1))
        list.add(getUser(2))
        list.add(getUser(3))

        return list
    }

    private suspend fun getUser(id:Int) : String{
        delay(1000)
        return "User$id"
    }


}