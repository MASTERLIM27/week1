package com.example.appdev_week1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import model.user;

public class addUser extends AppCompatActivity {

    private TextInputLayout add_textInputLayout_name,add_textInputLayout_age,add_textInputLayout_address;
    private Button add_button_save;
    private Toolbar add_toolbar;
    private ArrayList<user> list;
    private ProgressBar add_progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        initView();
        setListener();
    }

    private void initView(){
        add_textInputLayout_name = findViewById(R.id.add_textInputLayout_name);
        add_textInputLayout_age = findViewById(R.id.add_textInputLayout_age);
        add_textInputLayout_address = findViewById(R.id.add_textInputLayout_address);
        add_button_save = findViewById(R.id.add_button_save);
        add_toolbar = findViewById(R.id.add_toolbar);
        add_progressBar = findViewById(R.id.add_progressBar);
    }

    private void setListener(){
        Intent intent = getIntent();
        int position = intent.getIntExtra("position",-1);
        list = intent.getParcelableArrayListExtra("arraylist");
        if(list==null){
            list= new ArrayList<>();
        }
        String condition = intent.getStringExtra("condition");
        if(condition.equalsIgnoreCase("add")){
            add_toolbar.setTitle("Add User");
            add_button_save.setText("Save Data");
            add_button_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    add_progressBar.setVisibility(View.VISIBLE);
                    String name = add_textInputLayout_name.getEditText().getText().toString().trim();
                    String age = add_textInputLayout_age.getEditText().getText().toString().trim();
                    String address = add_textInputLayout_address.getEditText().getText().toString().trim();
                    user temp = new user(name,age,address);
                    list.add(temp);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            add_progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(addUser.this,MainActivity.class);
                            intent.putExtra("arraylist",list);
                            startActivity(intent);
                            finish();
                        }
                    },1000);
                }
            });
        }else if(condition.equalsIgnoreCase("edit")){
            add_toolbar.setTitle("Edit User");
            add_button_save.setText("Update Data");
            add_textInputLayout_name.getEditText().setText(list.get(position).getName());
            add_textInputLayout_age.getEditText().setText(list.get(position).getAge());
            add_textInputLayout_address.getEditText().setText(list.get(position).getAddress());
            add_button_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    add_progressBar.setVisibility(View.VISIBLE);
                    String name = add_textInputLayout_name.getEditText().getText().toString().trim();
                    String age = add_textInputLayout_age.getEditText().getText().toString().trim();
                    String address = add_textInputLayout_address.getEditText().getText().toString().trim();
                    user temp = new user(name,age,address);
                    list.set(position,temp);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            add_progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(addUser.this,MainActivity.class);
                            intent.putExtra("arraylist",list);
                            startActivity(intent);
                            finish();
                        }
                    },1000);
                }
            });
        }

        add_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}