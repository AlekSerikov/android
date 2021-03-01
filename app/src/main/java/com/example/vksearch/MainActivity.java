package com.example.vksearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vksearch.utils.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText searchField;
    private Button searchButton;
    private TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.et_search_field);
        searchButton = findViewById(R.id.b_search_vk);
        result = findViewById(R.id.tv_result);

        //create listener
        System.out.println(searchField.getText().toString() + "!!!!!!!!!!!!!!");
        URL generatedUrl = NetworkUtils.generateUrl(searchField.getText().toString());
//        View.OnClickListener onClickListener = v -> result.setText(generatedUrl.toString());

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(searchField.getText().toString() + "!!!!!!!!!!!!!!");
            }
        };

        //set listener to button
        searchButton.setOnClickListener(onClickListener);
    }
}