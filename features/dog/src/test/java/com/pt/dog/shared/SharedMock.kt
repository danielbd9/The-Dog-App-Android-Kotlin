package com.pt.dog.shared

import com.pt.dog.model.Breeds
import com.pt.dog.model.BreedsImage

object SharedMock {

     fun getBreed() = Breeds(
        1,
        "Bulldog",
        "BreedFor",
        "BreedGroup",
        "BreedOrigin",
        "BreedTemperament",
         "ImageId",
        BreedsImage("url")
    )
}