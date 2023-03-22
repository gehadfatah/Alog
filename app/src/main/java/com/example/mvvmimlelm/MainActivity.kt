package com.example.mvvmimlelm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import java.util.*
import kotlin.system.measureTimeMillis

val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
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
    suspend fun getFirstsnaem(userId: List<String>): List<String> {
        val firNames= mutableListOf<Deferred<String>>()
        supervisorScope {
            for (us in userId) {
                val first=  async {
                    getFirstncll(us)
                }
                firNames.add(first)
            }
        }

        return firNames.awaitAll()
    }

    suspend fun getFirstncll(us: String): String {
        delay(1000L)
        return "akf afl"

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

class viewmodel : ViewModel() {
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


object SumOfPairs {
    @JvmStatic
    fun main(args: Array<String>) {
        val numbers = intArrayOf(1,5,8,1,2)
        val numbersWithDuplicates = intArrayOf(1, 2, 4, 3, 5, 6, -2, 4, 7, 8, 9, 1, 2)
        printPairs(numbers, 13)
      //  printPairs(numbersWithDuplicates, 8)
        var names = listOf("apple", "apple", "orange", "orange", "banana", "pears", "grape", "apricots")
        val frequencyMap: MutableMap<String, Int> = HashMap()
        var arr = arrayOf("b", "e", "a", "f", "d", "c")
        //arr.sort()
       // arr= arr.toSet().toList().toTypedArray()
        Arrays.sort(arr)
        //Arrays.sort(names)
        for (x in arr) print("$x  ")
        // names= names.toSet().toList()
        for (s in names) {
            var count = frequencyMap[s]
            if (count == null) count = 0
            frequencyMap[s] = count + 1
        }

        println(frequencyMap)
    }

    fun printPairs(givenArray: IntArray, givenSum: Int) {
        println("Given Array: " + Arrays.toString(givenArray))
        println("Given Sum : $givenSum")
        println("Integer numbers, whose sum is equal to : $givenSum")
        for (i in givenArray.indices) {
            val first = givenArray[i]
            for (j in i + 1 until givenArray.size) {
                val second = givenArray[j]
                if (first + second == givenSum) {
                    System.out.printf("(%d, %d) %n", first, second)
                }
            }
        }


        println()
    }
}