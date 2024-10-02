package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var view = findViewById<TextView>(R.id.testView)
        // You can test your helper functions by  calling them from onCreate() and
        // printing their output to the Log, which is visible in the LogCat:
        // eg. Log.d("function output", getTestDataArray().toString())
        Log.d("Data Array: ", getTestDataArray().toString())
        val dubArray = listOf(1.0,2.0,3.0,4.0,4.0)
        Log.d("Less than Median: ", averageLessThanMedian(dubArray).toString())
        view = getView(0, view, getTestDataArray(), this)
    }


    /* Convert all the helper functions below to Single-Expression Functions using Scope Functions */
    // eg. private fun getTestDataArray() = ...

    // HINT when constructing elaborate scope functions:
    // Look at the final/return value and build the function "working backwards"

    // Return a list of random, sorted integers
    private fun getTestDataArray() = MutableList(10) { Random.nextInt() }.apply { sort() }

    // Return true if average value in list is greater than median value, false otherwise
    private fun averageLessThanMedian(listOfNumbers: List<Double>) = listOfNumbers.run {
        val avg = average()
        sorted().let { sortedList ->
            val median = if (sortedList.size % 2 == 0)
                (sortedList[sortedList.size / 2] + sortedList[(sortedList.size - 1) / 2]) / 2
            else
                sortedList[sortedList.size / 2]
            avg < median
        }
    }

    // Create a view from an item in a collection, but recycle if possible (similar to an AdapterView's adapter)
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context) =
        (recycledView as? TextView ?: TextView(context).apply {
            setPadding(5, 10, 10, 0)
            textSize = 22f
        }).also { it.text = collection[position].toString() }
}