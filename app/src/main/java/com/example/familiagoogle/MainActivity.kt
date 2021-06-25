package com.example.familiagoogle

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.telephony.SmsManager
import android.util.Log
import android.widget.*
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.ads.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

   // private lateinit var binding: ActivityMainBinding
    private lateinit var map: GoogleMap
    private var msg: ImageButton? = null
    private var call: ImageButton? = null
    private var img: ImageView? = null
    private val cameraRequestId = 1222
    private var next: ImageButton? = null
    private var indice = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createMapFragment()
        val sur = intent.getSerializableExtra("obj") as Sucursales

        setTitle(sur.getSucursales()[indice].farmacia + " de "+ sur.getSucursales()[indice].localidad )
        call = findViewById(R.id.call)
        call?.setOnClickListener(){
                calling();
         }

        msg = findViewById(R.id.msg)
        msg?.setOnClickListener(){
            message();
        }

        img = findViewById(R.id.image)
        img?.setOnClickListener(){
            if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA),cameraRequestId)
            val cameraInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraInt, cameraRequestId)

        }

        next = findViewById(R.id.next)
        next?.setOnClickListener(){
            cambiarMarker()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == cameraRequestId){
            val Images: Bitmap = data?.extras?.get("data") as Bitmap
            img?.setImageBitmap(Images)
        }
    }

    fun  calling(){
        val sur = intent.getSerializableExtra("obj") as Sucursales

        val num =  sur.getSucursales()[1].num
        try {
            if (Build.VERSION.SDK_INT > 22) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this@MainActivity,
                        arrayOf(Manifest.permission.CALL_PHONE),
                        101
                    )
                    return
                }
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:" + num.toInt())
                startActivity(callIntent)
            } else {
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:" + num.toInt())
                startActivity(callIntent)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun message(text: String?){
        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
    }


    fun message(){
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 1)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Message")
        val input = EditText(this@MainActivity)
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT)
        input.layoutParams = lp
        builder.setView(input)
        builder.setPositiveButton("Send") { dialog, which ->
            try {
                val sms = SmsManager.getDefault()
                sms.sendTextMessage(60716066.toString(), null, input.text.toString(), null, null)
                Toast.makeText(this@MainActivity, "SMS Sent Successfully", Toast.LENGTH_SHORT).show()
            } catch (e: java.lang.Exception) {
                Toast.makeText(this@MainActivity, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel", null)
        val dialog = builder.create()
        dialog.show()
    }

    private fun createMapFragment( ) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.isMyLocationEnabled()
        createMarker()
    }

    private fun createMarker( ) {
        val sur = intent.getSerializableExtra("obj") as Sucursales

        Log.d("TAGGGGGGGGGGGGGGGGGGGG",  sur.getSucursales()[indice].localidad +  sur.getSucursales()[indice].x )
        setTitle(sur.getSucursales()[indice].farmacia + " de "+ sur.getSucursales()[indice].localidad )
        val favoritePlace = LatLng(sur.getSucursales()[indice].x ,sur.getSucursales()[indice].y)
        map.addMarker(MarkerOptions().position(favoritePlace).title(sur.getSucursales()[indice].localidad))
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(favoritePlace, 18f),
            4000,
            null
        )


    }

    fun cambiarMarker() {
        if(indice<2){
            indice++
            createMarker()
        }else{
            indice=0
            createMarker()
        }

    }

/*
     private val casa = LatLng( 10.2751126, -83.7618182)
     private val uni = LatLng(9.971366433626022, -84.12855865257377)

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