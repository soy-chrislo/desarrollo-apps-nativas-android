package com.example.appsnativasucompensar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appsnativasucompensar.database.DbUsuarios

class LoginActivity : AppCompatActivity() {
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var textName: EditText
    private lateinit var textPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        textName = findViewById(R.id.input_name)
        textPassword = findViewById(R.id.input_password)
        btnLogin = findViewById(R.id.btn_login)
        btnRegister = findViewById(R.id.btn_register)

        btnRegister.setOnClickListener {
            val dbUsuarios = DbUsuarios(this)
            val id = dbUsuarios.insertUser(textName.text.toString(), textPassword.text.toString())

            if (id > 0) {
                Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
                cleanFields()
            } else {
                Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
            }
        }

        btnLogin.setOnClickListener {
            val dbUsuarios = DbUsuarios(this)
            val userId = dbUsuarios.getUser(textName.text.toString(), textPassword.text.toString())

            if (userId > 0) {
                Toast.makeText(this, "Usuario autenticado", Toast.LENGTH_SHORT).show()
                cleanFields()

                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario no autenticado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun cleanFields() {
        textName.text.clear()
        textPassword.text.clear()
    }
}