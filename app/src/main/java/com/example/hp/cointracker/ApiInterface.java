package com.example.hp.cointracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("/v2/ticker/?start=0&limit=100&sort=id&structure=array")
    Call<Coin> getCoins();
@GET("/v2/listings/")
    Call<Pojo> getList();

}
