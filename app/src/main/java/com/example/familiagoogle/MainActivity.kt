package com.example.familiagoogle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import com.google.android.gms.ads.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.util.Log

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createMapFragment()
    }
    private fun createMapFragment( ) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
    }

    private fun createMarker( ) {
        val x = intent.getSerializableExtra( "x" ).toString()
        val y = intent.getSerializableExtra( "y" ).toString()
        val t = intent.getSerializableExtra( "title" ).toString()
        val favoritePlace = LatLng(x.toDouble(),y.toDouble())
        map.addMarker(MarkerOptions().position(favoritePlace).title(t))
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(favoritePlace, 18f),
            4000,
            null
        )
    }

/*
     private val casa = LatLng( 10.2751126, -83.7618182)
     private val uni = LatLng(9.971366433626022, -84.12855865257377)
     private var entra:Boolean = true

    fun cambiarMarker(view: View) {
        if(entra){
            createMarker(casa.latitude, casa.longitude, "Mi casa")
            entra=false;
        }else{
            createMarker(uni.latitude, uni.longitude, "Mi Universidad")
            entra=true;
        }
    }

    private var p:Boolean = true

    fun CambiarVista(view: View) {
        if(p){
            map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            p=false;
        }else{
            map.mapType = GoogleMap.MAP_TYPE_NORMAL
            p=true;
        }
    }
*/


}