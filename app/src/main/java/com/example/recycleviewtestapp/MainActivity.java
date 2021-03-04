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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numbersList = findViewById(R.id.rv_numbers);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //как будут располагаться элементы в LV
        numbersList.setLayoutManager(layoutManager);

        numbersList.setHasFixedSize(true);


        IntStream.range(1, 100).boxed().collect(Collectors.toList());

    }
}