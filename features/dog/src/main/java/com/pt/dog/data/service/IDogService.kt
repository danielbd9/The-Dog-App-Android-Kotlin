package com.pt.dog.data.service

import com.pt.dog.model.Breeds
import com.pt.dog.model.BreedsImage
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IDogService {
    @GET("v1/breeds?limit=10")
    suspend fun getBreeds(@Query("page") page: Int): List<Breeds>

    @GET("v1/breeds/search")
    suspend fun getSearchBreeds(@Query("q") query: String): List<Breeds>

    @GET("v1/breeds/{id}")
    suspend fun getBreedsById(@Path("id") id: Int): Breeds

    @GET("v1/images/{id}")
    suspend fun getBreedsImageById(@Path("id") id: String): BreedsImage
}