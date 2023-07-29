package com.pt.dog.usecase

import com.pt.dog.data.DogRepository
import com.pt.dog.model.Breeds
import javax.inject.Inject

class BreedsSearchUseCase @Inject constructor(private val dogRepository: DogRepository) {

    suspend fun getSearchBreeds(term: String): List<Breeds> {
        return dogRepository.getSearchBreeds(term)
    }
}