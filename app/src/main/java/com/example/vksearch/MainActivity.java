package com.example.vksearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vksearch.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private EditText searchField;
    private Button searchButton;
    private TextView result;
    private TextView errorMessage;
    private ProgressBar loadingIndicator;

    private void showResultTextView() { //show result, hide error
        result.setVisibility(View.VISIBLE);
        errorMessage.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessageTextView() {
        result.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.VISIBLE);

    }

    //param, progress, result
    class VKQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() { //start showing progress bar
            loadingIndicator.setVisibility(View.VISIBLE);
        }

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
            if (response != null && !response.isEmpty()) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("response");

                    StringBuilder resultingString = new StringBuilder();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject userInfo = jsonArray.getJSONObject(i);
                        name = userInfo.getString("first_name");
                        lastName = userInfo.getString("last_name");

                        resultingString.append("Name: ").append(name).append("\n").append("Last name: ").append(lastName).append("\n\n");
                    }
                    result.setText(resultingString.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                showResultTextView();
            } else {
                showErrorMessageTextView();
            }
            loadingIndicator.setVisibility(View.INVISIBLE); //end showing progress bar
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.et_search_field);
        searchButton = findViewById(R.id.b_search_vk);
        result = findViewById(R.id.tv_result);
        errorMessage = findViewById(R.id.tv_error_message);
        loadingIndicator = findViewById(R.id.pb_loading_indicator);

        //create listener
        View.OnClickListener onClickListener = v -> {
            if (searchField.getText().toString().isEmpty()) {
                result.setText("Input id!");
            } else {
                URL generatedUrl = NetworkUtils.generateUrl(searchField.getText().toString());
                new VKQueryTask().execute(generatedUrl);
            }
        };
        //set listener to button
        searchButton.setOnClickListener(onClickListener);
    }
}