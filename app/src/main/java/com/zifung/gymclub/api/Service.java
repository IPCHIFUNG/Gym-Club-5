package com.zifung.gymclub.api;

import com.zifung.gymclub.user.PostUser;
import com.zifung.gymclub.user.UserBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Service {

    public static final  String base_url = "http://192.168.222.150:8000/";

    @POST("/api/user/register")
    Call<UserBean> register(@Body PostUser user);

    @POST("/api/user/login")
    Call<UserBean> login(@Body PostUser user);
}
