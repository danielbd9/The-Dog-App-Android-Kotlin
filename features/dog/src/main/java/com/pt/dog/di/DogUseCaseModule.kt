package com.pt.dog.di

import com.pt.dog.data.DogRepository
import com.pt.dog.usecase.DogUseCase
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
    fun provideDogUseCase(dogRepository: DogRepository): DogUseCase {
        return DogUseCase(dogRepository)
    }
}
