package com.manrique.taximetro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback
import retrofit2.Call ;
import retrofit2.Response;
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import kotlin.collections.forEach

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnComenzar.setOnClickListener {
            val intent:Intent=Intent(this, Maps::class.java)
            startActivity(intent)
        }

        val service = Retrofit.Builder()
            .baseUrl("http://192.168.1.5:8000/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(Laravel::class.java)

        service.getMovieById(1)
            .enqueue(object : Callback<List<hola>> {

                override fun onResponse(call:Call<List<hola>>, response: Response<List<hola>>) {
                    println("ya salio bien hasta aqui")
                   response.body()?.forEach  {
                   }
                }
                override fun onFailure(call: Call<List<hola>>, t: Throwable){
                    t.printStackTrace()
                    println("si valio vergas")
                }
            })



    }
}
