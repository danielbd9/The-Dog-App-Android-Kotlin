package com.pt.dog.data

import com.pt.dog.data.service.DogApiService
import com.pt.dog.model.Dog
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(dogApiService: DogApiService) :
    DogRepository(dogApiService) {

    override suspend fun getDogs(): List<Dog> {
        return  dogApiService.getDogs()
    }
}