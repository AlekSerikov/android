package com.example.firstandroidapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    private TextView plList;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plList = findViewById(R.id.tv_pl_names);

        plList.setText("");

        String [] names = {"Java", "Python", "Ruby", "JavaScript", "Mathlab"};

        Arrays.stream(names).forEach(s -> plList.append(s + "\n"));
 //sdsdsdsdsd
    }
}