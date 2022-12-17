package com.example.doglist.model.dao

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface apiAdapter {
    @GET
    suspend fun getDogsbyName(@Url url: String): Response<dogListEntity>
}