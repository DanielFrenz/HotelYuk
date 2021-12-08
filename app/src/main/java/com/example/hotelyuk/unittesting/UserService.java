package com.example.hotelyuk.unittesting;

import com.example.hotelyuk.api.ApiClient;
import com.example.hotelyuk.api.ApiInterface;
import com.example.hotelyuk.apiresponse.UserResponse;
import com.example.hotelyuk.entity.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService {
    public void register(final UserView view, User user, final UserCallback callback)
    {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> userDAOCall =
                apiService.registerUser(user);
        userDAOCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call,
                                   Response<UserResponse> response) {

                if(response.body().getMessage().equalsIgnoreCase("Register Success"
                )){
                    callback.onSuccess(true,
                            response.body().getUser());
                }
                else{
                    callback.onError();
                    view.showRegisterError(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                view.showErrorResponse(t.getMessage());
                callback.onError();
            }
        });
    }

    public Boolean getValid(final UserView view, User user) {
        final Boolean[] bool = new Boolean[1];
        register(view, user, new UserCallback() {
            @Override
            public void onSuccess(boolean value, User user) {
                bool[0] = true;
            }

            @Override
            public void onError() {
                bool[0] = false;
            }
        });
        return bool[0];
    }
}
