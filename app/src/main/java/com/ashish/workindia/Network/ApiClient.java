package com.ashish.workindia.Network;
import com.ashish.workindia.Cons;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {



    private static ApiInterface requestInterface;

    public static ApiInterface getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().
                readTimeout(40, TimeUnit.SECONDS)
                .connectTimeout(40, TimeUnit.SECONDS)
                .addInterceptor(interceptor).
                        build();

        if(requestInterface==null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            requestInterface = new Retrofit.Builder()
                    .baseUrl(Cons.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build().create(ApiInterface.class);
        }
        return requestInterface;
    }
}
