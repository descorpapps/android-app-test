package com.testapp.sg.app.Api;

import com.testapp.sg.app.Models.ForgotPasswordModel;
import com.testapp.sg.app.Models.LoginModel;
import com.testapp.sg.app.Models.LoginResponse;
import com.testapp.sg.app.Models.ProjectModel;
import com.testapp.sg.app.Models.ProjectModelList;
import com.testapp.sg.app.Models.ProjectUpdate;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginModel model);

    @POST("forgot-password")
    Call<Void> forgotPassword(@Body ForgotPasswordModel model);

    @GET("projects-manage/index")
    Call<ProjectModelList> getCompanies(@Header("Authorization") String auth);

    @POST("auth/logout")
    Call<Void> logout(@Header("Authorization") String auth);

    @POST("projects-manage/update")
    Call<ProjectModel> update(@Header("Authorization") String auth, @Query("id") int id, @Body ProjectUpdate model);
}
