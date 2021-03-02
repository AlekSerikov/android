package com.example.vksearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vksearch.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText searchField;
    private Button searchButton;
    private TextView result;

    //param, progress, result
    class VKQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) { //обрабатываем запрос
            String response = null;

            try {
                response = NetworkUtils.getResponseFromUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String response) { //после обработки запроса в фоне
            String name = null;
            String lastName = null;

            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                JSONObject userInfo = jsonArray.getJSONObject(0);
                name = userInfo.getString("first_name");
                lastName = userInfo.getString("last_name");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            result.setText("Name: " + name + "\n" + "Last name: " + lastName); //добавляем рез в наше view
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.et_search_field);
        searchButton = findViewById(R.id.b_search_vk);
        result = findViewById(R.id.tv_result);

        //create listener
        View.OnClickListener onClickListener = v -> {
            URL generatedUrl = NetworkUtils.generateUrl(searchField.getText().toString());
            new VKQueryTask().execute(generatedUrl);
        };

        //set listener to button
        searchButton.setOnClickListener(onClickListener);
    }
}