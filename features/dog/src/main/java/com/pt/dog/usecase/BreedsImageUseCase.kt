package com.pt.dog.usecase

import com.pt.dog.data.DogRepository
import com.pt.dog.model.BreedsImage
import javax.inject.Inject

class BreedsImageUseCase @Inject constructor(private val dogRepository: DogRepository) {

    suspend fun getBreedsImageById(id: String): BreedsImage {
        return dogRepository.getBreedsImageById(id)
    }
}