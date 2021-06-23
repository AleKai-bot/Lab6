package com.example.familiagoogle

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.example.familiagoogle.databinding.ActivityMenuBinding

class Menu : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var fishel : ImageView = binding.fishel
        var bomba : ImageView = binding.bomba
        var total : ImageView = binding.total
        var value : ImageView = binding.value

        fishel.setOnClickListener(){
            val x = 9.9677707
            val y = -84.1205752
            val title = "Fishel"
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("x", x)
            intent.putExtra("y", y)
            intent.putExtra("title", title)
            startActivity(intent)
        }

    }


}