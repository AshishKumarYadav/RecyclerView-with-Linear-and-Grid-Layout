package com.ashish.workindia.Network;

import com.ashish.workindia.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/v3/b6a30bb0-140f-4966-8608-1dc35fa1fadc")
    Call<ResponseModel> getResponse();

}
