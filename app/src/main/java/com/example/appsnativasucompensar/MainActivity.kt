package com.example.appsnativasucompensar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

// Login, Lista Productos, Carrito de Compras y Registro Clientes
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnStart = findViewById<AppCompatButton>(R.id.btnStart)
        var etName = findViewById<AppCompatEditText>(R.id.etName)

        btnStart.setOnClickListener{
            val name = etName.text.toString();
            if (name.isNotEmpty()) {
                Log.i("Chrislo", "Botón pulsado ${etName.text.toString()}")
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("EXTRA_NAME", name)
                startActivity(intent)

            }
        }
    }
}