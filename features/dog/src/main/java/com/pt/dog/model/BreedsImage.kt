package com.pt.dog.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BreedsImage(
    val url: String?
): Parcelable
