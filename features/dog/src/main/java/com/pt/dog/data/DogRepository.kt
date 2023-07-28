package com.pt.dog.data

import com.pt.dog.data.service.DogApiService
import com.pt.dog.model.Dog
import javax.inject.Inject


open class DogRepository @Inject constructor(val dogApiService: DogApiService) {
    open suspend fun getDogs(): List<Dog> {
        return dogApiService.getDogs()
    }
}

