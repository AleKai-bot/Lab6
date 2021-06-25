package com.example.familiagoogle
import android.util.Log
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.familiagoogle.databinding.ActivityMenuBinding
import com.google.android.material.snackbar.Snackbar

class Menu : AppCompatActivity(), OnInitListener {

    private lateinit var binding: ActivityMenuBinding

    private val RECONOCEDOR_VOZ = 7
    private var escuchando: TextView? = null
    private var respuesta: TextView? = null
    private var respuest: java.util.ArrayList<Respuestas>? = null
    private var leer: TextToSpeech? = null
    private var asistente = Asistente()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var fishel : ImageView = binding.fishel
        var bomba : ImageView = binding.bomba
        var total : ImageView = binding.total
        var value : ImageView = binding.value
        var voz : ImageView = binding.micro

        inicializar()
        asistente()

        fishel.setOnClickListener(){
            var farmaciaHE = Sucursal("Heredia",9.993865, -84.125749, "Fishel",22378266)
            var farmaciaOX = Sucursal( "Oxígeno",9.9884029, -84.1224547,"Fishel",22378266)
            var farmaciaSP = Sucursal( "San Pablo",9.9852752, -84.1172191,"Fishel", 22378266)
            var obj = Sucursales(arrayListOf(farmaciaHE,farmaciaOX,farmaciaSP));
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("obj", obj)
            startActivity(intent)
        }

        bomba.setOnClickListener(){
            var farmaciaHE = Sucursal("Coronado",9.9667289, -84.1254061, "La Bomba",22378266)
            var farmaciaOX = Sucursal( "Centro Heredia",9.9667289, -84.1254061,"La Bomba",22378266)
            var farmaciaSP = Sucursal( "Alajuela",9.974337, -84.1880625,"La Bomba", 22378266)
            var obj = Sucursales(arrayListOf(farmaciaHE,farmaciaOX,farmaciaSP));
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("obj", obj)
            startActivity(intent)
        }


        total.setOnClickListener(){
            var farmaciaHE = Sucursal("La 32",10.2178712,-83.8391657, "Farmacia Total",22378266)
            var farmaciaOX = Sucursal( "Guápiles",10.2178712, -83.8391657,"Farmacia Total", 22378266)
            var farmaciaSP = Sucursal("Cariari",9.976211, -84.1578135, "Farmacia Total",22378266)
            var obj = Sucursales(arrayListOf(farmaciaHE,farmaciaOX,farmaciaSP));
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("obj", obj)
            startActivity(intent)
        }

        value.setOnClickListener(){
            var farmaciaHE = Sucursal("Zapote",9.92048, -84.0634633, "Farmacia Value",22378266)
            var farmaciaOX = Sucursal( "Hatillo",9.9248254, -84.0921646,"Farmacia Value",22378266)
            var farmaciaSP = Sucursal( "Escazú",9.9435763, -84.1463619,"Farmacia Value", 22378266)
            var obj = Sucursales(arrayListOf(farmaciaHE,farmaciaOX,farmaciaSP));
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("obj", obj)
            startActivity(intent)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun inicializar() {
        escuchando = binding.tvEscuchado
        respuesta = binding.tvRespuesta
        leer = TextToSpeech(this, this)
        Log.d("s", leer.toString())
        respuest = asistente.proveerDatos()
        asistente = Asistente(escuchando,respuesta,respuest,leer)
    }

    fun hablar(){
        var hablar =  Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        hablar.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX");
        startActivityForResult(hablar, RECONOCEDOR_VOZ);
    }

    fun asistente(){
        var voz : ImageView = binding.micro
        voz.setOnClickListener { view -> hablar()
            Snackbar.make(view, "Tu asistente", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == AppCompatActivity.RESULT_OK && requestCode == RECONOCEDOR_VOZ){
            var reconocido = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            var escuchado = reconocido?.get(0)
            escuchando?.setText(escuchado)
            asistente.prepararRespuesta(escuchado)
        }
    }

    override fun onInit(status: Int) {

    }

}