package com.pt.dog.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Breeds (
    val id: Int = 0,
    val name: String?,
    val bred_for: String?,
    val breed_group: String?,
    val origin: String?,
    val temperament: String?,
    val image: BreedsImage?
): Parcelable