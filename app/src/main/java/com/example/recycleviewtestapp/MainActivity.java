package com.example.recycleviewtestapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {
    private RecyclerView numbersList;
    private NumbersAdaptor adaptor;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numbersList = findViewById(R.id.rv_numbers); //RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //как будут располагаться элементы в RV
        numbersList.setLayoutManager(layoutManager);
        numbersList.setHasFixedSize(true); //фиксированное кол элементов

        adaptor = new NumbersAdaptor(100); //создаем адаптер
        numbersList.setAdapter(adaptor);//устанавливаем адаптер для RV

    }
}