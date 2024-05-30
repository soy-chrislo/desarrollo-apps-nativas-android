package com.example.appsnativasucompensar

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appsnativasucompensar.adapters.UserListAdapter
import com.example.appsnativasucompensar.database.DbUsuarios
import com.example.appsnativasucompensar.entities.User

class UsersListActivity : AppCompatActivity() {
    private lateinit var userListView: RecyclerView
    private lateinit var userList: ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)
        userListView = findViewById(R.id.listaUsuarios)
        userListView.layoutManager = LinearLayoutManager(this)

        val dbUsuarios = DbUsuarios(this)
        userList = ArrayList<User>();

        val listAdapter = UserListAdapter(dbUsuarios.users);
        userListView.adapter = listAdapter;
    }
}