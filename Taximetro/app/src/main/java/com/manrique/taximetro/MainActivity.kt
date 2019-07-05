package com.manrique.taximetro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_maps.*
import retrofit2.Callback
import retrofit2.Call ;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import kotlin.collections.forEach
import com.manrique.taximetro.Maps as Maps1

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnComenzar.setOnClickListener {
            val intent:Intent=Intent(this, Maps1::class.java)
            startActivity(intent)
        }

    }
}
