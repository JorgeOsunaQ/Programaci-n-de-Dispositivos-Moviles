package com.itcuandroid.ciclovida

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG="MainActivity"
    private lateinit var logView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logView=findViewById(R.id.LogView)

        Log.d(TAG, "Se llama al método onCreate")
        addLog("Se llama al método onCreate")
    }

    private fun addLog(message: String){
        val text=LogView.text;

        logView.text=text.toString() + message + "\n";
    }

    override fun onStart() {
        super.onStart()

        Log.d(TAG, "Se llama al método onStart")
        addLog("*Se llama al método onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.d(TAG, "Se llama al método onResume")
        addLog("Se llama al método onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.d(TAG, "Se llama al método onPause")
        addLog("Se llama al método onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.d(TAG, "Se llama al método onStop")
        addLog("Se llama al método onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "Se llama al método onDestroy")
        addLog("Se llama al método onDestroy")
    }


}