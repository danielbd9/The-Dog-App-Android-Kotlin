package com.pt.dog.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.pt.dog.model.Breeds
import com.pt.dog.model.BreedsImage
import com.pt.dog.shared.SharedMock
import com.pt.dog.ui.details.BreedsDetailViewModel
import com.pt.dog.usecase.BreedsDetailUseCase
import com.pt.dog.usecase.BreedsImageUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class BreedsDetailViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var breedsDetailUseCase: BreedsDetailUseCase

    @MockK
    private lateinit var breedsImageUseCase: BreedsImageUseCase

    @MockK
    private lateinit var breedsViewModel: BreedsDetailViewModel

    private val url = "url"
    private val networkError = "Network error"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)

        breedsViewModel = BreedsDetailViewModel(breedsDetailUseCase,breedsImageUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given that the user wants to see race details When clicking Then it should display the card with details`() {

        // Given
        val breedId = 1
        val breed = SharedMock.getBreed()
        val breedImage = BreedsImage(url)
        coEvery { breedsDetailUseCase.getBreedsById(breedId) } returns breed
        coEvery { breedsImageUseCase.getBreedsImageById(breed.reference_image_id.orEmpty()) } returns breedImage
        val breedsLiveDataObserver = mockk<Observer<Breeds>>(relaxed = true)
        breedsViewModel.breedsLiveData.observeForever(breedsLiveDataObserver)

        // When
        breedsViewModel.getBreedsById(breedId)

        // Then
        verify { breedsLiveDataObserver.onChanged(breed) }
    }

    @Test
    fun `Given that user wants to see race details When clicked and has no internet connection Then should not display card with details`() {
        // Given
        val breedId = 1
        coEvery { breedsDetailUseCase.getBreedsById(breedId) } throws IOException(networkError)
        val breedsErrorLiveDataObserver = mockk<Observer<String>>(relaxed = true)
        breedsViewModel.breedsErrorLiveData.observeForever(breedsErrorLiveDataObserver)

        // When
        breedsViewModel.getBreedsById(breedId)

        // Then
        verify { breedsErrorLiveDataObserver.onChanged(networkError) }
    }

    @Test
    fun `Given that the user wants to see details of the race When clicking Then he must display the image on the card`() {
        // Given
        val referenceImageId = "reference_image_id"
        val breedImage = BreedsImage(url)
        coEvery { breedsImageUseCase.getBreedsImageById(referenceImageId) } returns breedImage
        val breedsImageLiveDataObserver = mockk<Observer<BreedsImage>>(relaxed = true)
        breedsViewModel.breedsImageLiveData.observeForever(breedsImageLiveDataObserver)

        // When
        breedsViewModel.getBreedsImageById(referenceImageId)

        // Then
        verify { breedsImageLiveDataObserver.onChanged(breedImage) }
    }

    @Test
    fun `Given that the user wants to see details of the race When clicked and has no internet connection Then should not display card with details`() {
        // Given
        val referenceImageId = "reference_image_id"
        coEvery { breedsImageUseCase.getBreedsImageById(referenceImageId) } throws IOException(networkError)
        val breedsErrorLiveDataObserver = mockk<Observer<String>>(relaxed = true)
        breedsViewModel.breedsErrorLiveData.observeForever(breedsErrorLiveDataObserver)

        // When
        breedsViewModel.getBreedsImageById(referenceImageId)

        // Then
        verify { breedsErrorLiveDataObserver.onChanged(networkError) }
    }
}
