package com.example.appdev_week1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import model.user;

public class detail_user extends AppCompatActivity {

    private TextView detail_textView_name, detail_textView_age, detail_textView_address;
    private Button detail_button_edit, detail_button_delete;
    private Toolbar detail_toolbar;
    private ImageView detail_imageView;
    private ArrayList<user> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        initView();
        setListener();
    }

    private void initView(){
        detail_textView_name = findViewById(R.id.detail_textView_name);
        detail_textView_age = findViewById(R.id.detail_textView_age);
        detail_textView_address = findViewById(R.id.detail_textView_address);
        detail_button_edit = findViewById(R.id.detail_button_edit);
        detail_button_delete = findViewById(R.id.detail_button_delete);
        detail_toolbar = findViewById(R.id.detail_toolbar);
        detail_imageView = findViewById(R.id.detail_imageView);
    }

    private void setListener(){
        Intent intent = getIntent();
        list = intent.getParcelableArrayListExtra("arraylist");
        int position = intent.getIntExtra("position",-1);
        detail_textView_name.setText(list.get(position).getName());
        detail_textView_age.setText(list.get(position).getAge());
        detail_textView_address.setText(list.get(position).getAddress());

        detail_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        detail_button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detail_user.this, addUser.class);
                intent.putExtra("condition","edit");
                intent.putExtra("position",position);
                intent.putParcelableArrayListExtra("arraylist",list);
                startActivity(intent);
            }
        });

        detail_button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(detail_user.this)
                        .setIcon(R.drawable.delete2)
                        .setTitle("Apakah Anda Yakin Ingin Menghapus "+ list.get(position).getName() +" ?")
                        .setMessage("Semua data "+ list.get(position).getName() +" akan terhapus")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                list.remove(position);
                                Intent intent = new Intent(detail_user.this,MainActivity.class);
                                intent.putParcelableArrayListExtra("arraylist",list);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            }
        });
    }
}