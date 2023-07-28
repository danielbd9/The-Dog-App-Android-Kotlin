package com.pt.dog.di

import com.pt.dog.data.service.DogApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideDogApiService(retrofit: Retrofit): DogApiService {
        return retrofit.create(DogApiService::class.java)
    }
}
