package com.example.mvvmimlelm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.launch

val TaAG = "MainActivity"

class MainaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*   runBlocking {
               launch {
                 //  delay(3000L)
                   Log.d(TAG, "excute launch")

               }
               launch {
                   //  delay(3000L)
                   Log.d(TAG, "excute launch 2")

               }
               Log.d(TAG, "onCreate: after launche start delay")
               delay(5000L)
               Log.d(TAG, "onCreate: after delay")
           }
           Log.d("","after run blocking")
   */

        /* val job = GlobalScope.launch(Dispatchers.IO) {
             repeat(5) {
                  Log.d(TAG, "coroutine still waiting: ") // this called 4 times
                  delay(1000L)
             }

         }
         runBlocking {
             delay(2000L)
             job.cancel()
           //  job.join()
             Log.d(TAG, "onCreate: finish after join main thread contirue")
         }*/

        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                var answer1: String? = null
                var answer2: String? = null
                 val networkcall1=async {  networkCall1()}
                //val networkcall1 = launch { answer1 = networkCall1() }
                val networkcall2 = launch { answer2 = networkCall2() }
                // Log.d(TAG, "ans 1 : ${networkcall1.await()}")
                // networkcall2.join()
                //networkcall1.join()

               // Log.d(TAG, "ans 1 : ${answer1}")
                Log.d(TAG, "ans 1 : ${ networkcall1.await()}")
                Log.d(TAG, "ans 2 : ${answer2}")
            }
            Log.d(TAG, "onCreate: time is $time")
            adfk()
        }


    }

    suspend fun networkCall1(): String {
        delay(3000L)
        Log.d(TAG, "networkCall1: ")
        return "answer 1"
    }

    suspend fun networkCall2(): String {
        delay(3000L)
        Log.d(TAG, "networkCall2: ")
        return "answer 2"
    }

    val coroutinesExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            println("exceptin is ${throwable.localizedMessage}")
        }

    suspend fun adfk() {

        coroutineScope {

        }
        supervisorScope {

        }
        val handler = CoroutineExceptionHandler { _, throwable ->
            println("exceptian $throwable")
        }
        CoroutineScope(/*Dispatchers.Main+*/handler).launch {

            launch {
                delay(300)
                throw java.lang.Exception()
            }
            launch {
                //if it 300 it will print
                //fix this by add supervisiorscope
                delay(400)
                Log.d(TAG, "adfk: secone 2")
            }
        }
    }
}

class vieswmodel : ViewModel() {
    val coroutinesExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            println("exceptin is ${throwable.localizedMessage}")
        }
    val _MutableLiveData = MutableLiveData<String>()

    val liveDate
        get() = _MutableLiveData

    fun fetchDataWithExHandler() {
        viewModelScope.launch(coroutinesExceptionHandler) {
            launch {
                throw IllegalStateException("Error thrown in fetchDataWithExHandler")
            }
        }
    }
}