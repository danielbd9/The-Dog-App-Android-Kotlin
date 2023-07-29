package com.pt.dog.usecase

import com.pt.dog.data.DogRepository
import com.pt.dog.model.Breeds
import javax.inject.Inject

class BreedsUseCase @Inject constructor(private val dogRepository: DogRepository) {

    suspend fun getDogs(page: Int): List<Breeds> {
        return dogRepository.getDogs(page)
    }
}