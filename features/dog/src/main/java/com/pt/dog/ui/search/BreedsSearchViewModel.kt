package com.pt.dog.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pt.dog.model.Breeds
import com.pt.dog.usecase.BreedsSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedsSearchViewModel @Inject constructor(
    private val breedsSearchUseCase: BreedsSearchUseCase) : ViewModel() {

    private val _breedsLiveData: MutableLiveData<List<Breeds>> = MutableLiveData()
    val breedsLiveData: LiveData<List<Breeds>> get() = _breedsLiveData

    private val _breedsErrorLiveData: MutableLiveData<String> = MutableLiveData()
    val breedsErrorLiveData: LiveData<String> get() = _breedsErrorLiveData

    private val _breedsErrorSearchLiveData: MutableLiveData<Unit> = MutableLiveData()
    val breedsErrorSearchLiveData: LiveData<Unit> get() = _breedsErrorSearchLiveData

    fun getSearchBreeds(term: String) {
        viewModelScope.launch {
            try {
                val dogs = breedsSearchUseCase.getSearchBreeds(term)

                if (dogs.isEmpty())
                    _breedsErrorSearchLiveData.value = Unit
                else
                    _breedsLiveData.value = dogs.sortedBy { p -> p.name }
            } catch (e: Exception) {
                val error = "Network error"
                _breedsErrorLiveData.value = error
            }
        }
    }
}