package com.example.hotelyuk.api;

import com.example.hotelyuk.apiresponse.HotelResponse;
import com.example.hotelyuk.apiresponse.KamarResponse;
import com.example.hotelyuk.apiresponse.ReviewResponse;
import com.example.hotelyuk.apiresponse.UserResponse;
import com.example.hotelyuk.entity.LoginData;
import com.example.hotelyuk.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    //AuthController
    @Headers({"Accept: application/json"})
    @POST("api/register")
    Call<UserResponse> registerUser(@Body User user);

    @Headers({"Accept: application/json"})
    @POST("api/login")
    Call<UserResponse> loginUser(@Body LoginData loginData);

    @Headers({"Accept: application/json"})
    @GET("api/logout")
    Call<UserResponse> logout(@Header("Authorization") String token);

    //UserController
    @Headers({"Accept: application/json"})
    @PUT("api/user")
    Call<UserResponse> editUser(@Header("Authorization") String token,
                                @Body User user);

    //HotelController
    @Headers({"Accept: application/json"})
    @GET("api/hotel")
    Call<HotelResponse> getAllHotel(@Header("Authorization") String token);

    @Headers({"Accept: application/json"})
    @GET("api/hotel/{id}")
    Call<HotelResponse> getHotelById(@Header("Authorization") String token,
                                     @Path("id") long id);

    //ReviewController
    @Headers({"Accept: application/json"})
    @GET("api/hotel/{hotelId}/review")
    Call<ReviewResponse> getListReviewByHotel(@Header("Authorization") String token,
                                              @Path("hotelId") long hotelId);

    @Headers({"Accept: application/json"})
    @GET("api/user/review")
    Call<ReviewResponse> getListReviewByUser(@Header("Authorization") String token);

    //KamarController
    @Headers({"Accept: application/json"})
    @GET("api/hotel/{hotelId}/kamar")
    Call<KamarResponse> getListKamar(@Header("Authorization") String token,
                                              @Path("hotelId") long hotelId);
}
