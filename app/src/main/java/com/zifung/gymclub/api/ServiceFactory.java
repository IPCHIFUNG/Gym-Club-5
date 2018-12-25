package com.zifung.gymclub.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {
    public static <T> T getService(Class<T> tClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Service.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(tClass);
    }
}
