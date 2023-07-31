package com.pt.dog.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.pt.dog.model.Breeds
import com.pt.dog.shared.SharedMock
import com.pt.dog.ui.list.BreedsViewModel
import com.pt.dog.usecase.BreedsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BreedsViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var breedsUseCase: BreedsUseCase

    @MockK
    private lateinit var breedsViewModel: BreedsViewModel

    private val networkError = "Network error"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)

        breedsViewModel = BreedsViewModel(breedsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given that the user wants to list the breeds When searching Then it should display a list`() = runBlocking {
        // Given
        val currentPage = 0
        val nextPage = currentPage + 1
        val mockBreeds = listOf(SharedMock.getBreed())

        coEvery { breedsUseCase.getBreeds(currentPage) } returns mockBreeds
        coEvery { breedsUseCase.getBreeds(nextPage) } returns mockBreeds

        val breedsObserver = mockk<Observer<List<Breeds>>>(relaxed = true)
        breedsViewModel.breedsLiveData.observeForever(breedsObserver)

        // When
        breedsViewModel.loadBreeds()

        // Then

        verify {
            breedsObserver.onChanged(mockBreeds)
        }
    }

    @Test
    fun `Given that the user wants to load more list the breeds When searching Then it should display a list`() = runBlocking {
        // Given
        val currentPage = 0
        val nextPage = currentPage + 1
        val mockBreeds = listOf(SharedMock.getBreed())

        coEvery { breedsUseCase.getBreeds(currentPage) } returns mockBreeds
        coEvery { breedsUseCase.getBreeds(nextPage) } returns mockBreeds

        val breedsObserver = mockk<Observer<List<Breeds>>>(relaxed = true)
        breedsViewModel.breedsLiveData.observeForever(breedsObserver)

        // When
        breedsViewModel.loadMoreBreeds()

        // Then

        verify {
            breedsObserver.onChanged(mockBreeds)
        }
    }

    @Test
    fun `Given that the user wants to list the breeds When an error occurred in the search Then it must present an error to the user`() = runBlocking {
        // Given
        val currentPage = 0

        coEvery { breedsUseCase.getBreeds(currentPage) } throws Exception(networkError)

        val errorObserver = mockk<Observer<String>>(relaxed = true)
        breedsViewModel.breedsErrorLiveData.observeForever(errorObserver)

        // When
        breedsViewModel.loadMoreBreeds()

        // Then
        verify {
            errorObserver.onChanged(networkError)
        }
    }
}
