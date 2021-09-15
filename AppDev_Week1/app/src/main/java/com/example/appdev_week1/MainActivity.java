package com.example.appdev_week1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import model.user;

public class MainActivity extends AppCompatActivity implements OnCardListener {

    private TextView Main_TextView_noData;
    private FloatingActionButton Main_Button_Tambah;
    private RecyclerView user_RecyclerView;
    private ArrayList<user> dataUser;
    private UserRVAdapter adapter;
    private ProgressBar Main_progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
//        addDummyData();
        setupRecyclerView();
        setListener();
    }

    private void initView(){
        user_RecyclerView = findViewById(R.id.user_RecyclerView);
        Main_Button_Tambah = findViewById(R.id.Main_Button_Tambah);
        Main_progressBar = findViewById(R.id.Main_progressBar);
        Main_TextView_noData = findViewById(R.id.Main_TextView_noData);
        Intent intent = getIntent();
        dataUser = intent.getParcelableArrayListExtra("arraylist");
        if(dataUser==null){
            dataUser = new ArrayList<>();
        }
        adapter = new UserRVAdapter(dataUser, this);

        if(dataUser.size() != 0){
            Main_TextView_noData.setVisibility(View.GONE);
        }else {
            Main_TextView_noData.setVisibility(View.VISIBLE);
        }
    }

    private void setupRecyclerView(){
        Main_progressBar.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        user_RecyclerView.setLayoutManager(manager);
        user_RecyclerView.setAdapter(adapter);
        Main_progressBar.setVisibility(View.GONE);
    }

    private void setListener(){
        Main_Button_Tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), addUser.class);
                intent.putExtra("condition","add");
                intent.putParcelableArrayListExtra("arraylist",dataUser);
                startActivity(intent);
            }
        });
    }

    private void addDummyData(){
        dataUser.add(new user("a","100","jl mahmud"));
        dataUser.add(new user("a","100","jl mahmud"));
        dataUser.add(new user("a","100","jl mahmud"));
        dataUser.add(new user("a","100","jl mahmud"));
        dataUser.add(new user("a","100","jl mahmud"));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCardClick(int position) {
        Intent intent = new Intent(getApplicationContext(), detail_user.class);
        startActivity(intent);
    }
}