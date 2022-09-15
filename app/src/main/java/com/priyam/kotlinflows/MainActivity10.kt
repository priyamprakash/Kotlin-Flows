package com.priyam.kotlinflows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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
    }
}