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

class LoginActivity : AppCompatActivity() {
    private lateinit var btnLogin: Button
    private lateinit var textName: EditText
    private lateinit var textPassword: EditText
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        textName = findViewById(R.id.input_name)
        textPassword = findViewById(R.id.input_password)
        btnLogin = findViewById(R.id.btn_login)
        toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        btnLogin.setOnClickListener {
            val dbUsuarios = DbUsuarios(this)
            val userId = dbUsuarios.getUser(textName.text.toString(), textPassword.text.toString())

            if (userId > 0) {
                Toast.makeText(this, "Usuario autenticado", Toast.LENGTH_SHORT).show()
                cleanFields()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario no autenticado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.login_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.register_user -> {
                Toast.makeText(this, "Registro de Usuario", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, UserRegisterActivity::class.java)
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