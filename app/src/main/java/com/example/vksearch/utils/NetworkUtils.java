package com.example.vksearch.utils;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NetworkUtils {
    private static final String VK_API_BASE_URL = "https://api.vk.com";
    private static final String VK_USERS_GET = "/method/users.get";
    private static final String PARAM_USER_ID = "user_ids";
    private static final String PARAM_VERSION = "v";
    private static final String PARAM_ACCESS_TOKEN = "access_token";
    private static final String ACCESS_TOKEN_VALUE
            = "9931d1ca9931d1ca9931d1ca0b99477f99999319931d1caf90c3a6e1339b0e00ae4b545";

    public static URL generateUrl(String userIds) {
        Uri buildUri = Uri.parse(VK_API_BASE_URL + VK_USERS_GET)
                .buildUpon()
                .appendQueryParameter(PARAM_USER_ID, userIds)
                .appendQueryParameter(PARAM_VERSION, "5.89")
                .appendQueryParameter(PARAM_ACCESS_TOKEN, ACCESS_TOKEN_VALUE)
                .build(); //Uri - универсальный идентификатор ресурса

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream(); //получаем http ответ

//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A"); //формируем целую строку

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            }
            return null;
        } catch (UnknownHostException e) {
            return null;
        } finally {
            urlConnection.disconnect();
        }
    }
}
