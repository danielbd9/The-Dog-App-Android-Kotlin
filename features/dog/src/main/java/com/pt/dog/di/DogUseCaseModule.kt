package com.pt.dog.di

import com.pt.dog.data.DogRepository
import com.pt.dog.usecase.BreedsDetailUseCase
import com.pt.dog.usecase.BreedsImageUseCase
import com.pt.dog.usecase.BreedsSearchUseCase
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
    fun provideListBreedsUseCase(dogRepository: DogRepository): BreedsUseCase {
        return BreedsUseCase(dogRepository)
    }

    @Provides
    @Singleton
    fun provideSearchBreedsUseCase(dogRepository: DogRepository): BreedsSearchUseCase {
        return BreedsSearchUseCase(dogRepository)
    }

    @Provides
    @Singleton
    fun provideDetailBreedsUseCase(dogRepository: DogRepository): BreedsDetailUseCase {
        return BreedsDetailUseCase(dogRepository)
    }

    @Provides
    @Singleton
    fun provideImageBreedsUseCase(dogRepository: DogRepository): BreedsImageUseCase {
        return BreedsImageUseCase(dogRepository)
    }
}
