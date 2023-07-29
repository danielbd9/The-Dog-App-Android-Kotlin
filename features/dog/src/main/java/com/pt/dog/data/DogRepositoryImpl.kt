package com.pt.dog.data

import com.pt.dog.data.service.DogApiService
import com.pt.dog.model.Breeds
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(dogApiService: DogApiService) :
    DogRepository(dogApiService) {

    override suspend fun getBreeds(page: Int): List<Breeds> {
        return  dogApiService.getBreeds(page)
    }

    override suspend fun getSearchBreeds(term: String): List<Breeds> {
        return  dogApiService.getSearchBreeds(term)
    }
}