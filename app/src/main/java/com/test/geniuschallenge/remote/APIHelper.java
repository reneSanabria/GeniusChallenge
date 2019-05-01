package com.test.geniuschallenge.remote;

public class APIHelper {
    private APIHelper(){
    };

    public static final String API_URL = "https://reqres.in/api/";

    public static UserService getUserService(){
        return RetrofitSingleton.getClient(API_URL).create(UserService.class);
    }

}
