package com.example.appsnativasucompensar

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appsnativasucompensar.database.DbUsuarios
import com.example.appsnativasucompensar.entities.User

class UserUpdateActivity : AppCompatActivity() {
    private lateinit var txtName: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnSave: Button

    private lateinit var user: User
    private var id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_update)

        txtName = findViewById(R.id.txtName)
        txtPassword = findViewById(R.id.txtPassword)
        btnSave = findViewById(R.id.btnSave)

        if (savedInstanceState == null) {
            val extras = intent.extras;
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = savedInstanceState.getSerializable("ID") as Int;
        }

        val userDb = DbUsuarios(this)
        user = userDb.getUser(id)

        if (user != null) {
            txtName.setText(user.name)
            txtPassword.setText(user.password)
        }

        btnSave.setOnClickListener {
            if (!(txtName.text.toString().isEmpty() || txtPassword.text.toString().isEmpty())) {
                user.name = txtName.text.toString()
                user.password = txtPassword.text.toString()
                val result = userDb.updateUser(id, user.name, user.password)

                if (result) {
                    Toast.makeText(this, "Usuario actualizado correctamente", Toast.LENGTH_SHORT).show()
                    // showSave()
                    val listIntent = Intent(this, UsersListActivity::class.java)
                    startActivity(listIntent)
                } else {
                    Toast.makeText(this, "Error al actualizar el usuario", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showSave() {
        intent = Intent(this, UserViewActivity::class.java)
        intent.putExtra("ID", id)
        startActivity(intent)
    }
}