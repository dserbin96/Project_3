package com.example.dns.project_3.domain;

import com.example.dns.project_3.entity.Phone;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestLenta {

    @POST("/sign-in/")
    Call<String> postPnone(@Body Phone phone);

}