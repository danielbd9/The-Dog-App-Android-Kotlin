package com.pt.dog.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pt.dog.model.Breeds
import com.pt.dog.model.BreedsImage
import com.pt.dog.usecase.BreedsDetailUseCase
import com.pt.dog.usecase.BreedsImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedsDetailViewModel @Inject constructor(
    private val breedsDetailUseCase: BreedsDetailUseCase,
    private val breedsImageUseCase: BreedsImageUseCase
    ) : ViewModel() {

    private val _breedsLiveData: MutableLiveData<Breeds> = MutableLiveData()
    val breedsLiveData: LiveData<Breeds> get() = _breedsLiveData

    private val _breedsImageLiveData: MutableLiveData<BreedsImage> = MutableLiveData()
    val breedsImageLiveData: LiveData<BreedsImage> get() = _breedsImageLiveData

    private val _breedsErrorLiveData: MutableLiveData<String> = MutableLiveData()
    val breedsErrorLiveData: LiveData<String> get() = _breedsErrorLiveData

    fun getBreedsById(id: Int) {
        viewModelScope.launch {
            try {
                val breed = breedsDetailUseCase.getBreedsById(id)

                getBreedsImageById(breed.reference_image_id.orEmpty())

                _breedsLiveData.value = breed

            } catch (e: Exception) {
                 val error = "Network error"
                _breedsErrorLiveData.value = error
            }
        }
    }

    fun getBreedsImageById(id: String) {
        viewModelScope.launch {
            try {
                val dogs = breedsImageUseCase.getBreedsImageById(id)
                _breedsImageLiveData.value = dogs
            } catch (e: Exception) {
                val error = "Network error"
                _breedsErrorLiveData.value = error
            }
        }
    }
}