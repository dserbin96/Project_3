package com.example.dns.project_3.domain;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class LentaNetwork {

    public final static String URL_LENTA = "";
    private static RequestLenta requestLenta;

    private LentaNetwork() {
    }

    public static RequestLenta getInstance() {
        if (requestLenta == null) {
            HttpLoggingInterceptor loggin = new HttpLoggingInterceptor().
                    setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(loggin);
            Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL_LENTA)
                    .addConverterFactory(GsonConverterFactory.create());

            builder.client(httpClient.build());
            Retrofit retrofit = builder.build();

            requestLenta = retrofit.create(RequestLenta.class);
        }
        return requestLenta;
    }
}