package com.pt.dog.di

import com.pt.dog.data.DogRepository
import com.pt.dog.usecase.BreedsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DogUseCaseModule {

    @Provides
    @Singleton
    fun provideDogUseCase(dogRepository: DogRepository): BreedsUseCase {
        return BreedsUseCase(dogRepository)
    }
}
