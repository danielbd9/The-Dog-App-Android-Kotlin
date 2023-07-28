package com.pt.dog.usecase

import com.pt.dog.data.DogRepository
import com.pt.dog.model.Dog
import javax.inject.Inject

class DogUseCase @Inject constructor(private val dogRepository: DogRepository) {

    suspend fun getDogs(): List<Dog> {
        return dogRepository.getDogs()
    }
}