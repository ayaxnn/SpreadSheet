package com.example.spreadsheet

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var name = findViewById<EditText>(R.id.etname)
        var year = findViewById<EditText>(R.id.etyear)
        var print = findViewById<EditText>(R.id.etprintname)
        var size = findViewById<EditText>(R.id.etsize)
        var savedata = findViewById<Button>(R.id.btnSave)

        savedata.setOnClickListener {
            if (name.text.toString().isEmpty() or year.text.toString()
                    .isEmpty() or print.text.toString().isEmpty() or size.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Please Fill All The Entries", Toast.LENGTH_SHORT).show()
            } else {
                val url = "https://script.google.com/macros/s/AKfycbztzbP0rUouysTLPJNi0l54N-82A__jy02NkdYEx1lZ26lJi3VW8OuVh4ZwwZMQlZik/exec"
                val stringRequest = object : StringRequest(Request.Method.POST, url,
                    Response.Listener {
                                      Toast.makeText(this , it.toString() , Toast.LENGTH_SHORT).show()
                    },
                    Response.ErrorListener {
                        Toast.makeText(this , it.toString() , Toast.LENGTH_SHORT).show()
                    }){
                    override fun getParams(): MutableMap<String, String>? {
                        val params = HashMap<String,String>()
                        params["Name"]=name.text.toString()
                        params["Year"]=year.text.toString()
                        params["Size"]=size.text.toString()
                        params["Print"]=size.text.toString()
                        return params
                    }
                }
                val queue= Volley.newRequestQueue(this)

                queue.add(stringRequest)
            }

        }

    }
}


