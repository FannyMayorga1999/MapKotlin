package com.manrique.taximetro

import android.content.pm.PackageManager
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
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

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
    }

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
            Map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong,17f))

            }

            val latitudeNew = location.latitude
            val longitudeNew = location.longitude
            val latitudeOld = lastLocation.latitude
            val longitudeOld = lastLocation.longitude
            println(latitudeNew)
            println(longitudeNew)
            println(latitudeOld)
            println(longitudeOld)

        }
    }
}