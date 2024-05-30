package com.example.appsnativasucompensar

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appsnativasucompensar.database.DbUsuarios

class UserRegisterActivity : AppCompatActivity() {
    private lateinit var btnRegister: Button
    private lateinit var textName: EditText
    private lateinit var textPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)

        textName = findViewById(R.id.username)
        textPassword = findViewById(R.id.password)
        btnRegister = findViewById(R.id.register)

       val toolbar = findViewById<Toolbar>(R.id.register_toolbar)

        setSupportActionBar(toolbar)

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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.register_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.return_home -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun cleanFields() {
        textName.text.clear()
        textPassword.text.clear()
    }
}