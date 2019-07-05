package com.manrique.taximetro

import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class Maps : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var Map: GoogleMap


    /* Ayuda al ser zoom en mapa*/
    override fun onMarkerClick(p0: Marker?): Boolean = false

    /*variable que se inicia instancia de uan clase que nos ayuda a tener a acceso a la localizacion*/
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    /*funcion de la ultima localizacion*/
    private lateinit var lastLocation : Location
    /* nos da el codigo para el permiso*/
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(this)

        /* funcion para obtener la localizacion*/
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        Map = googleMap
        Map.setOnMarkerClickListener(this)
        Map.uiSettings.isZoomControlsEnabled = true
        setUnMap();

        /*Map!!.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))

        Map!!.addMarker(MarkerOptions().position(opera).title("Opera House"))

        val options = PolylineOptions()
        options.color(Color.RED)
        options.width(5f)
        val url = getURL(sydney, opera)*/


    }


    /*private fun getURL(from : LatLng, to : LatLng) : String {

        val origin = "origin=" + from.latitude + "," + from.longitude
        val dest = "destination=" + to.latitude + "," + to.longitude
        val sensor = "sensor=false"

        val params = "$origin&$dest&$sensor"
        return "https://maps.googleapis.com/maps/api/directions/json?$params"
    }


    }
*/
    /*le da los permisos para gps para denegar o aceptar la localizacion*/
    private fun setUnMap() {

        if (ActivityCompat.checkSelfPermission
                (this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }


        /*capara para despejar como estamos*/
        Map.isMyLocationEnabled = true

        fusedLocationProviderClient.lastLocation.addOnSuccessListener(this) {
                location -> if(location != null){
            /*captura la posicion de las variables en eso momento sino nos muestra la ultimo lugar donde estuvo*/
                    lastLocation = location
                    var currentLatLong = LatLng(location.latitude,location.longitude)
                    var hola = LatLng(-0.2301762, -78.5240735)
                    Map.animateCamera(CameraUpdateFactory.newLatLngZoom(hola,17f))

                    Map.addMarker(MarkerOptions()
                    .position(currentLatLong)
                    .title("Origen")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))).showInfoWindow();


                    Map.addMarker(MarkerOptions()
                    .position(hola)
                    .title("Destino")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));


                    /*val latitudeNew = location.latitude
                    val longitudeNew = location.longitude
                    val latitudeOld = lastLocation.latitude
                    val longitudeOld = lastLocation.longitude


                    println(latitudeNew)
                    println(longitudeNew)
                    println(latitudeOld)
                    println(longitudeOld)*/

            }

        }


        val service = Retrofit.Builder()
            .baseUrl("http://192.168.1.4:8000/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(Laravel::class.java)

        service.getMap()
            .enqueue(object : Callback<List<SaveMap>> {
                override fun onResponse(call: Call<List<SaveMap>>, response: Response<List<SaveMap>>) {
                    println("ya salio bien hasta aqui")
                    response.body()?.forEach {
                        println("${it}")
                    }
                }
                override fun onFailure(call: Call<List<SaveMap>>, t: Throwable){
                    t.printStackTrace()
                    println("si valio vergas")
                }
            })

        /*service.posMap(name = "lee", nameAdress = "hola", latitudeNew = 52,longitudeNew =58,latitudeOld = 25,longitudeOld = 67)
            .enqueue(object : Callback<List<SaveMap>> {
                override fun onResponse(call: Call<List<SaveMap>>, response: Response<List<SaveMap>>) {
                    response.body()?.forEach {
                        println("${it}")
                    }
                }
                override fun onFailure(call: Call<List<SaveMap>>, t: Throwable){
                    t.printStackTrace()
                    println("si valio vergas")
                }

            })*/
    }
}