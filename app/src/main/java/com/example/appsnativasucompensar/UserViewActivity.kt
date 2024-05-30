package com.example.appsnativasucompensar

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appsnativasucompensar.database.DbUsuarios
import com.example.appsnativasucompensar.entities.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UserViewActivity : AppCompatActivity() {
    private lateinit var txtName: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnSave: Button
    private lateinit var fabEdit: FloatingActionButton
    private lateinit var fabRemove: FloatingActionButton

    private lateinit var user: User
    private var id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_view)

        txtName = findViewById(R.id.txtName)
        txtPassword = findViewById(R.id.txtPassword)
        btnSave = findViewById(R.id.btnSave)
        fabEdit = findViewById(R.id.fabEdit)
        fabRemove = findViewById(R.id.fabDelete)

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
            btnSave.visibility = Button.INVISIBLE
            txtName.inputType = EditorInfo.TYPE_NULL
            txtPassword.inputType = EditorInfo.TYPE_NULL
        }

        fabEdit.setOnClickListener {
            val intent = Intent(this, UserUpdateActivity::class.java)
            intent.putExtra("ID", id)
            startActivity(intent)
        }

        fabRemove.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("¿Está seguro de eliminar el usuario?")
                .setPositiveButton("SI") { dialog, which ->
                    val result = userDb.deleteUser(id)
                    if (result) {
                        val intent = Intent(this, UsersListActivity::class.java)
                        startActivity(intent)
                    }
                }
                .setNegativeButton("NO", {
                    dialog, which ->
                })
                .show();

        }
    }
}