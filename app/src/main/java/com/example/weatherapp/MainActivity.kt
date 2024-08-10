package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var apiKey="968f795a9d984f5b91f822cb85d66b34"
    lateinit var lat:EditText
    lateinit var lon:EditText
    lateinit var submit:Button
    lateinit var result:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        lat=findViewById(R.id.en_lat)
        lon=findViewById(R.id.en_lon)
        result=findViewById(R.id.result)
        submit=findViewById(R.id.submit)
        submit.setOnClickListener(this)
    }
    fun getWeather() {
        val slat=lat.text.toString()
        val slon=lon.text.toString()
        Log.d("hello ", slat)
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.openweathermap.org/data/2.5/weather?lat=$slat.34&lon=$slon.99&appid=968f795a9d984f5b91f822cb85d66b34"
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val weather=response.getJSONObject("main").getDouble("temp")
                result.text=weather.toString()
            },
            { error ->
                Log.e("error", error.toString())
            }

        )
        queue.add(jsonRequest)

    }

    override fun onClick(p0: View?) {
        getWeather()
    }

}