package com.example.appdev_week1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import model.user;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.UserViewHolder>{
    private ArrayList<user> listUser;
    protected OnCardListener cardListener;

    public UserRVAdapter(ArrayList<user> listUser, OnCardListener cardListener) {
        this.cardListener = cardListener;
        this.listUser = listUser;
    }

    @NonNull
    @Override
    public UserRVAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRVAdapter.UserViewHolder holder, int position) {
        holder.cardUser_textView_name.setText(listUser.get(position).getName());
        holder.cardUser_textView_age.setText(listUser.get(position).getAge());
        holder.cardUser_textView_address.setText(listUser.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView cardUser_textView_name,cardUser_textView_age,cardUser_textView_address;
        private ImageView cardUser_image;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            cardUser_textView_name = itemView.findViewById(R.id.cardUser_textView_name);
            cardUser_textView_age = itemView.findViewById(R.id.cardUser_textView_age);
            cardUser_textView_address = itemView.findViewById(R.id.cardUser_textView_address);
            cardUser_image = itemView.findViewById(R.id.cardUser_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),detail_user.class);
                    intent.putParcelableArrayListExtra("arraylist",listUser);
                    intent.putExtra("position",getAdapterPosition());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
