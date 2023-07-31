package com.pt.dog.model

data class Breeds (
    val id: Int = 0,
    val name: String?,
    val bred_for: String?,
    val breed_group: String?,
    val origin: String?,
    val temperament: String?,
    val reference_image_id: String?,
    val image: BreedsImage?
)