package com.pt.dog.data

import com.pt.dog.data.service.DogApiService
import com.pt.dog.model.Breeds
import javax.inject.Inject


open class DogRepository @Inject constructor(val dogApiService: DogApiService) {
    open suspend fun getBreeds(page: Int): List<Breeds> {
        return dogApiService.getBreeds(page)
    }

    open suspend fun getSearchBreeds(term: String): List<Breeds> {
        return dogApiService.getSearchBreeds(term)
    }
}

