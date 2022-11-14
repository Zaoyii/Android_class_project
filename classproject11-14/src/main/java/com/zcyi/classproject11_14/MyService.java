package com.zcyi.classproject11_14;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyService {

    @GET("/myweb/goods/goods_list_data.json")
    Call<ArrayList<Goods>> getJson();
}
