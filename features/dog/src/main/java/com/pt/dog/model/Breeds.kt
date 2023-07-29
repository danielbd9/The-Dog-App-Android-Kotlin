package com.pt.dog.model

data class Breeds (
    val id: Int,
    val name: String?,
    val bred_for: String?,
    val breed_group: String?,
    val origin: String?,
    val temperament: String?,
    val image: BreedsImage
)