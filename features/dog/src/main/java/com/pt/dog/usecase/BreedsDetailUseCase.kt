package com.pt.dog.usecase

import com.pt.dog.data.DogRepository
import com.pt.dog.model.Breeds
import javax.inject.Inject

class BreedsDetailUseCase @Inject constructor(private val dogRepository: DogRepository) {

    suspend fun getBreedsById(id: Int): Breeds {
        return dogRepository.getBreedsById(id)
    }
}