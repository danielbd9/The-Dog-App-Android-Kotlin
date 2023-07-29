package com.pt.dog.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pt.dog.model.Breeds
import com.pt.dog.usecase.BreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(private val dogUseCase: BreedsUseCase) : ViewModel() {

    private val _breedsLiveData: MutableLiveData<List<Breeds>> = MutableLiveData()
    val breedsLiveData: LiveData<List<Breeds>> get() = _breedsLiveData

    private val _breedsErrorLiveData: MutableLiveData<String> = MutableLiveData()
    val breedsErrorLiveData: LiveData<String> get() = _breedsErrorLiveData

    private var currentPage = 0

    fun fetchBreeds(page: Int) {
        viewModelScope.launch {
            try {
                val dogs = dogUseCase.getDogs(page)
                _breedsLiveData.value = dogs.sortedBy { p->p.name }
            } catch (e: Exception) {
                _breedsErrorLiveData.value = e.message
            }
        }
    }

    fun loadMoreBreeds() {
        currentPage++
        fetchBreeds(currentPage)
    }
}