package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText textEntry;
    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.b_change_activity);
        textEntry = findViewById(R.id.et_text_entry);

        button.setOnClickListener(v -> {
            Context context = MainActivity.this;
            Class <ChildActivity> destinationActivity = ChildActivity.class;
            Intent childActivityIntent = new Intent(context, destinationActivity);
//            childActivityIntent.putExtra("text", textEntry.getText().toString());
            childActivityIntent.putExtra(Intent.EXTRA_TEXT, textEntry.getText().toString());
            startActivity(childActivityIntent);
        });

    }
}