package com.test.geniuschallenge.remote;


import com.test.geniuschallenge.models.User;
import com.test.geniuschallenge.models.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @GET("users/")
    Call<UsersResponse> getUsersResponse();

    @POST("users/")
    Call<User> addUser(@Body User user);

}
