package com.example.appsnativasucompensar.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsnativasucompensar.R;
import com.example.appsnativasucompensar.UserViewActivity;
import com.example.appsnativasucompensar.entities.User;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    ArrayList<User> userList;

    public UserListAdapter(ArrayList<User> userList) {
        this.userList = userList;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_usuario, null, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.viewName.setText(userList.get(position).getName());
        holder.viewPassword.setText(userList.get(position).getPassword());
    }

    @Override
    public int getItemCount() {
        return this.userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView viewName, viewPassword;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            viewName = itemView.findViewById(R.id.viewUser);
            viewPassword = itemView.findViewById(R.id.viewPassword);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, UserViewActivity.class);
                    intent.putExtra("ID", userList.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
