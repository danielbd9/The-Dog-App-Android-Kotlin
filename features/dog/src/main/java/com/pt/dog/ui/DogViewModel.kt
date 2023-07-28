package com.pt.dog.ui

import androidx.lifecycle.ViewModel
import com.pt.dog.usecase.DogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(private val dogUseCase: DogUseCase) : ViewModel() {

}