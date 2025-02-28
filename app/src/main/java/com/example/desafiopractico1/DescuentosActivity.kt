package com.example.desafiopractico1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DescuentosActivity : AppCompatActivity() {

    private lateinit var usernameEditText : EditText
    private lateinit var salarioEditText : EditText
    private lateinit var buttonCalcular : Button
    private lateinit var linearLayoutResultado : LinearLayout // Este es el contenedor Layout que contendrá los resultados como texto.
    private lateinit var linearLayoutImageProgrammer: LinearLayout // este contenedor contendrá el texto e imagen utilizados mientras el usuario no llena el formulario.
    private lateinit var textViewRenta : TextView
    private lateinit var textViewAFP : TextView
    private lateinit var textViewISSS : TextView
    private lateinit var buttonLimpiar : Button
    private lateinit var textViewTotal : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_descuentos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        usernameEditText = findViewById(R.id.inputNombre)
        salarioEditText = findViewById(R.id.inputSalario)
        linearLayoutResultado = findViewById(R.id.linearLayoutResultado)
        linearLayoutImageProgrammer = findViewById(R.id.linearLayoutImageProgrammer)
        buttonCalcular = findViewById(R.id.btnCalcular)
        textViewRenta = findViewById(R.id.textViewRenta)
        textViewAFP = findViewById(R.id.textViewAFP)
        textViewISSS = findViewById(R.id.textViewISSS)
        buttonLimpiar = findViewById(R.id.btnLimpiarDesc)
        textViewTotal = findViewById(R.id.textViewTotal)


        buttonCalcular.setOnClickListener {
            val username = usernameEditText.text.toString()
            val salario = salarioEditText.text.toString().toDoubleOrNull()

            if (username.isNotEmpty() && salario != null) {
                val descuento = calcularDescuento(salario)

                // Cambio de la visibilidad de los elementos
                linearLayoutResultado.visibility = LinearLayout.VISIBLE
                linearLayoutImageProgrammer.visibility = LinearLayout.GONE

                textViewRenta.setText("Renta a descontar: $${descuento} mensual")

                var valorAFP = salario * 0.07
                val valorAFPString = formatToTwoDecimals(valorAFP)
                var valorISSS = salario * 0.03
                val valorISSSString = formatToTwoDecimals(valorISSS)
                var total = salario - descuento - valorAFP - valorISSS
                val totalString = formatToTwoDecimals(total)

                textViewAFP.setText("AFP: $${valorAFPString}")
                textViewISSS.setText("ISSS: $${valorISSSString}")
                textViewTotal.setText("Total salario neto: $${totalString}")

                buttonLimpiar.setOnClickListener {
                    usernameEditText.text.clear()
                    salarioEditText.text.clear()
                    linearLayoutResultado.visibility = LinearLayout.GONE
                    linearLayoutImageProgrammer.visibility = LinearLayout.VISIBLE
                }
                } else {
                    // Mostrar en un Toast un mensaje de error
                    Toast.makeText(this, "Por favor, ingrese un nombre de usuario y un salario válido", Toast.LENGTH_SHORT).show()
                }
            }
        }

    private fun calcularDescuento(salario: Double): Double {
        var renta = 0.0
         when {
            salario > 0.01 && salario <= 472.00 -> renta = 0.0
            salario > 472.01 && salario <= 895.24 -> renta = 0.10 * salario + 17.67
             salario > 895.25 && salario <= 2038.10 -> renta = 0.20 * salario + 60.00
             salario > 2038.11 -> renta = 0.30 * salario + 288.57
            else -> renta = 0.0
        }
        return renta
    }

    private fun formatToTwoDecimals(number: Double): String {
        return String.format("%.2f", number)
    }
}