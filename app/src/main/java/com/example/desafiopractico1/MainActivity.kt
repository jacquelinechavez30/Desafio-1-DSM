package com.example.desafiopractico1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        //variables para los  botones
        val btnPromedio = findViewById<Button>(R.id.btnPromedio)
        val btnDescuentos = findViewById<Button>(R.id.btnDescuentos)
        val btnCalculadora = findViewById<Button>(R.id.btnCalculadora)


        //boton promedio
        btnPromedio.setOnClickListener {
            val intent = Intent(this, PromedioActivity::class.java)
            startActivity(intent)
        }
        //boton Descuentos
        btnDescuentos.setOnClickListener {
            val intent = Intent(this, DescuentosActivity::class.java)
            startActivity(intent)
        }
        //boton calculadora
        btnCalculadora.setOnClickListener {
            val intent = Intent(this, CalculadoraActivity::class.java)
            startActivity(intent)
        }
    }
}