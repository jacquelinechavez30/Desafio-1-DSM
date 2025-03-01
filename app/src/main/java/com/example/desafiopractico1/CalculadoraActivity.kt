package com.example.desafiopractico1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow


class CalculadoraActivity : AppCompatActivity() {
    //para poder usarlos fuera de oncreate se declaran como variables globales
    private lateinit var display: TextView
    //variables para los numeros y operadores
    //almacenara el numero recién ingresado
    private var numeroActual = ""
    //almacenara el  operador escogido
    private var operador: String? = null
    //alamacenara el numero anterior que fue ingresado
    private var numeroAnterior = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculadora)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets // This line is required for insets handling
        }
        //pantalla(display)
        display = findViewById<TextView>(R.id.display)
        //variables para los numeros
        val num0 = findViewById<Button>(R.id.num0)
        val num1 = findViewById<Button>(R.id.num1)
        val num2 = findViewById<Button>(R.id.num2)
        val num3 = findViewById<Button>(R.id.num3)
        val num4 = findViewById<Button>(R.id.num4)
        val num5 = findViewById<Button>(R.id.num5)
        val num6 = findViewById<Button>(R.id.num6)
        val num7 = findViewById<Button>(R.id.num7)
        val num8 = findViewById<Button>(R.id.num8)
        val num9 = findViewById<Button>(R.id.num9)

        //variables para las operaciones
        val btnresta = findViewById<Button>(R.id.btnresta)
        val btnsuma = findViewById<Button>(R.id.btnsuma)
        val btnmultiplicacion = findViewById<Button>(R.id.btnmultiplicacion)
        val btndivision = findViewById<Button>(R.id.btndivision)
        val btnigual = findViewById<Button>(R.id.btnigual)
        val btnraizcuadrada = findViewById<Button>(R.id.btnraizcuadrada)
        val btnexponente = findViewById<Button>(R.id.btnexponente)
        val btnborrar = findViewById<Button>(R.id.btnborrar)

       //Asignacion de eventos a los botones para que tomen un valor
        num0.setOnClickListener { tomarElvalor("0") }
        num1.setOnClickListener { tomarElvalor("1") }
        num2.setOnClickListener { tomarElvalor("2") }
        num3.setOnClickListener { tomarElvalor("3") }
        num4.setOnClickListener { tomarElvalor("4") }
        num5.setOnClickListener { tomarElvalor("5") }
        num6.setOnClickListener { tomarElvalor("6") }
        num7.setOnClickListener { tomarElvalor("7") }
        num8.setOnClickListener { tomarElvalor("8") }
        num9.setOnClickListener { tomarElvalor("9") }
        //Asignacion de eventos a los botones para que tomen un operador
        btnresta.setOnClickListener { elegirOperador("-") }
        btnsuma.setOnClickListener { elegirOperador("+") }
        btnmultiplicacion.setOnClickListener { elegirOperador("*") }
        btndivision.setOnClickListener { elegirOperador("/") }
        btnraizcuadrada.setOnClickListener { elegirOperador("√") }
        btnexponente.setOnClickListener { elegirOperador("^") }
        //Asignacion de eventos a los botones para que realicen la operacion calcular y la funcion borrar
        btnigual.setOnClickListener { calcular() }
        btnborrar.setOnClickListener { borrar() }
    }

 //actualizar display
    private fun actualizarDisplay() {
        display.text = numeroActual
    }
    // ingreso de numeros
    private fun tomarElvalor(s: String) {
        numeroActual += s // Takes the value and concatenate with the other existing ones.
        actualizarDisplay() // Updates the value and shows it in the display
    }

    //boton de borrar
    private fun borrar() {
        this.numeroActual = ""
        this.numeroAnterior = ""
        this.operador = null
        actualizarDisplay()
    }

    //seleccionar operador
    fun elegirOperador(operator: String) {
        //Validar que se haya ingresado un numero  para poder escoger un operador
        if (numeroActual.isNotEmpty()) {
                this.numeroAnterior = this.numeroActual
                this.numeroActual = ""
                this.operador = operator

        } else {
            display.text = "Error debes ingresar un numero por favor"
        }

    }

  //boton igual
    private fun calcular() {
        if (this.numeroAnterior.isNotEmpty() &&  this.numeroActual.isNotEmpty() && this.operador != null) {
            val numero1 = this.numeroAnterior.toDouble()
            val numero2 = this.numeroActual.toDouble()
            var error: String? = null
            this.display.text // this will show the result of the operation
            //segun el operador se realizara la operacion

            //when funciona como un switch
            val result = when (operador) {
                "+" -> numero1 + numero2
                "-" -> numero1 - numero2
                "/" ->/*validacion*/ if (numero2 != 0.0) {
                    numero1 / numero2
                } else {
                    error= "Operacion indeterminada"

                }
                "*" -> numero1 * numero2
                "√" -> /*validacion*/ if (numero2 >= 0 && numero1 >0) {
                    numero2.pow(1/numero1) // The second number will be the radicanding not the radical
                } else {
                    error= "Operacion indeterminada"
                }
                "^" -> numero1.pow(numero2)

                else -> error= "Elige una operacion valida"
            }
            if (error != null) {
                display.text = error
                return
            }

            numeroActual = result.toString()
            numeroAnterior = ""
            operador = null
            actualizarDisplay()
        }
      else{
            display.text = "Error debes ingresar una operacion por favor"
        }
    }

}







