package com.example.archek.fitshedule.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FitService {

    @GET("get_group_lessons_v2/4/")
    Call<List<ObjectResponse>> getShedule();


}
