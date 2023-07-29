package com.pt.dog.data.service

import com.pt.dog.model.Breeds
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApiService {
    @GET("v1/breeds?limit=10")
    suspend fun getBreeds(@Query("page") page: Int): List<Breeds>

    @GET("v1/breeds/search")
    suspend fun getSearchBreeds(@Query("q") query: String): List<Breeds>

}