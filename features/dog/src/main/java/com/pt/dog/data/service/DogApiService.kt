package com.pt.dog.data.service

import com.pt.dog.model.Breeds
import retrofit2.http.GET

interface DogApiService {
    @GET("v1/breeds")
    suspend fun getDogs(): List<Breeds>
}