package com.example.desafiopractico1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PromedioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_promedio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //variables

        val nombre = findViewById<EditText>(R.id.nombre)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val nota1 = findViewById<EditText>(R.id.nota1)
        val nota2 = findViewById<EditText>(R.id.nota2)
        val nota3 = findViewById<EditText>(R.id.nota3)
        val nota4 = findViewById<EditText>(R.id.nota4)
        val nota5 = findViewById<EditText>(R.id.nota5)
        val resultado = findViewById<TextView>(R.id.resultado)
        val resultadonumero = findViewById<TextView>(R.id.resultadonumero)
        val resultadotitulo = findViewById<TextView>(R.id.resultadotitulo)
        val btnlimpiar = findViewById<Button>(R.id.btnlimpiar)
        val layoutResultado = findViewById<LinearLayout>(R.id.linearLayoutResPromedio)


        //boton calcular
        btnCalcular.setOnClickListener {

           if (nombre.text.toString().isEmpty()) {
                nombre.setError("Campo vacio")
            }
          else  if (nota1.text.toString().isEmpty() || nota1.text.toString().toDouble() < 0 || nota1.text.toString().toDouble() > 10) {
                nota1.setError("Revisa el campo no puede estar vacio o fuera de rango 0-10 ")
            }
           else if (nota2.text.toString().isEmpty() || nota2.text.toString().toDouble() < 0 || nota2.text.toString().toDouble() > 10) {
                nota2.setError("Revisa el campo no puede estar vacio o fuera de rango 0-10")
            }
           else if (nota3.text.toString().isEmpty() || nota3.text.toString().toDouble() < 0 || nota3.text.toString().toDouble() > 10) {
                nota3.setError("Revisa el campo no puede estar vacio o fuera de rango 0-10")
            }
           else if (nota4.text.toString().isEmpty() || nota4.text.toString().toDouble() < 0 || nota4.text.toString().toDouble() > 10) {
                nota4.setError("Revisa el campo no puede estar vacio o fuera de rango 0-10")
            }
           else  if (nota5.text.toString().isEmpty() || nota5.text.toString().toDouble() < 0 || nota5.text.toString().toDouble() > 10) {
                nota5.setError("Revisa el campo no puede estar vacio o fuera de rango 0-10")
            }

           else {

               layoutResultado.visibility = LinearLayout.VISIBLE
               var promedio = (nota1.text.toString().toDouble() * 0.15 +
                       nota2.text.toString().toDouble() * 0.15 +
                       nota3.text.toString().toDouble() * 0.20 +
                       nota4.text.toString().toDouble() * 0.25 +
                       nota5.text.toString().toDouble() * 0.25)
                val promedioString = formatToTwoDecimals(promedio)
                resultadonumero.setText(promedioString)
                if (promedio >= 6) {
                    resultado.setText("Aprobado")
                } else {
                    resultado.setText("Reprobado")
                }

           }
        }
        btnlimpiar.setOnClickListener{
            nombre.text.clear()
            nota1.text.clear()
            nota2.text.clear()
            nota3.text.clear()
            nota4.text.clear()
            nota5.text.clear()
            layoutResultado.visibility = LinearLayout.GONE
        }

    }


    private fun formatToTwoDecimals(number: Double): String {
        return String.format("%.2f", number)
    }
}