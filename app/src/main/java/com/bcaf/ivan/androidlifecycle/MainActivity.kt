package com.bcaf.ivan.androidlifecycle

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlinsharedpreference"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this@MainActivity, "onCreate", Toast.LENGTH_LONG).show()
        Log.d("Result : ", "onCreate")
        inp_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                val sharedPreferences: SharedPreferences =
                    getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("inpText", s.toString())
                editor.apply()
                editor.commit()
                Log.d("Report", "Done")
            }
        })

    }

    override fun onStart() {
        Toast.makeText(this@MainActivity, "onStart", Toast.LENGTH_LONG).show()
        Log.d("Result : ", "onStart")
        super.onStart()
    }

    override fun onDestroy() {
        Toast.makeText(this@MainActivity, "onDestroy", Toast.LENGTH_LONG).show()
        Log.d("Result : ", "onDestroy")
        super.onDestroy()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if(savedInstanceState!=null)
            Log.d("nilai",savedInstanceState.getString("nilai")!!)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("nilai","masuk")
        outState?.run {
            putString("nilai","Hai")
        }
    }
    override fun onResume() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val sharedIdValue = sharedPreferences.getString("inpText", "")
        if (sharedIdValue == "") {
            Toast.makeText(applicationContext, "Value null", Toast.LENGTH_LONG).show()
        } else {
            inp_text.setText(sharedIdValue)
        }
        Toast.makeText(this@MainActivity, "onResume", Toast.LENGTH_LONG).show()
        Log.d("Result : ", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("Result : ", "onPause")
        Toast.makeText(this@MainActivity, "onPause", Toast.LENGTH_LONG).show()
        super.onPause()
    }
}
