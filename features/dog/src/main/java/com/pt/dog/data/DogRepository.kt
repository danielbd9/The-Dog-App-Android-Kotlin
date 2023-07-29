package com.pt.dog.data

import com.pt.dog.data.service.DogApiService
import com.pt.dog.model.Breeds
import javax.inject.Inject


open class DogRepository @Inject constructor(val dogApiService: DogApiService) {
    open suspend fun getDogs(page: Int): List<Breeds> {
        return dogApiService.getDogs(page)
    }
}

