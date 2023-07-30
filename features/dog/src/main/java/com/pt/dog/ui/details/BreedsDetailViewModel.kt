package com.pt.dog.ui.details

import androidx.lifecycle.ViewModel
import com.pt.dog.usecase.BreedsSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BreedsDetailViewModel @Inject constructor(private val breedsSearchUseCase: BreedsSearchUseCase) :
    ViewModel() {


}