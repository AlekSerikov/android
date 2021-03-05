package com.example.recycleviewtestapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    private RecyclerView numbersRecyclerView;
    private NumbersAdaptor adaptor;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numbersRecyclerView = findViewById(R.id.rv_numbers); //RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //эл будут располагаться элементы в RV в виде списка
        numbersRecyclerView.setLayoutManager(layoutManager);
        numbersRecyclerView.setHasFixedSize(true); //фиксированное кол элементов

        adaptor = new NumbersAdaptor(100, this); //создаем адаптер
        numbersRecyclerView.setAdapter(adaptor);//устанавливаем адаптер для RV

    }
}