package com.example.myfirstandroidchallenge.viewmodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myfirstandroidchallenge.MainCoroutineRule
import com.example.myfirstandroidchallenge.data.repository.ProductRepository
import com.example.myfirstandroidchallenge.getOrAwaitValue
import com.example.myfirstandroidchallenge.test_data.TestData
import com.example.myfirstandroidchallenge.view.product.states.ProductLoadStates
import com.example.myfirstandroidchallenge.view_model.ProductsViewModel
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
internal class ProductViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineScope = MainCoroutineRule()

    private var productViewModel: ProductsViewModel? = null

    @Mock
    private lateinit var mProductRepository: ProductRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        productViewModel = ProductsViewModel(mProductRepository, coroutineScope.testDispatcher)
    }

    @After
    fun tearDown() {
        productViewModel = null
    }

    @Test
    fun `Test weather loading data saves it to database`() {
    }

    @Test
    fun `Test weather refreshing flushes and refreshes the data in database`() {
    }

    @Test
    fun `Test weather searching keyword, test 4, returns only one result`() {
    }

    @Test
    fun `loaded state should be raised when products loaded successfully`() {
        runTest(coroutineScope.testDispatcher) {
// Given
            Mockito.`when`(mProductRepository.getAllProductsWithReFetchIfNeeded()).thenReturn(TestData.ProductDOs)
            productViewModel = ProductsViewModel(mProductRepository, coroutineScope.testDispatcher)
// When
            productViewModel?.onViewCreated()
// then

// First state change should be loading
            val firstState = productViewModel?.productLoadStates?.getOrAwaitValue()
            Assert.assertTrue(firstState is ProductLoadStates.Loading)
// Second state change should be Error
            advanceUntilIdle()
// then
            val finalState = productViewModel?.productLoadStates?.getOrAwaitValue()
            Assert.assertTrue(finalState is ProductLoadStates.Loaded)
        }
    }

    @Test
    fun `error-or-empty state should be raised when product result is empty`() {
        runTest(coroutineScope.testDispatcher) {
// Given
            Mockito.`when`(mProductRepository.getAllProductsWithReFetchIfNeeded()).thenReturn(listOf())
            productViewModel = ProductsViewModel(mProductRepository, coroutineScope.testDispatcher)
// When
            productViewModel?.onViewCreated()
// then
// First state change should be loading
            val firstState = productViewModel?.productLoadStates?.getOrAwaitValue()

            Assert.assertTrue(firstState is ProductLoadStates.Loading)
// Second state change should be Error
            advanceUntilIdle()
// then
            val finalState = productViewModel?.productLoadStates?.getOrAwaitValue()
            Assert.assertTrue(finalState is ProductLoadStates.EmptyOrError)
        }
    }

    @Test
    fun `loaded state should be raised when products refreshed successfully`() {
        runTest(coroutineScope.testDispatcher) {
// Given
            Mockito.`when`(mProductRepository.getAllProductsWithReFetchIfNeeded(true))
                .thenReturn(TestData.ProductDOs)
            productViewModel = ProductsViewModel(mProductRepository, coroutineScope.testDispatcher)
// When
            productViewModel?.refreshProducts()
// then
            advanceUntilIdle()

// First state change should be loading
//            val firstState = productViewModel?.productLoadStates?.getOrAwaitValue()
//            Assert.assertTrue(firstState is ProductLoadStates.Loading)
// Second state change should be Error
//            advanceUntilIdle()
// then
            val finalState = productViewModel?.productLoadStates?.getOrAwaitValue()
            Assert.assertTrue(finalState is ProductLoadStates.Loaded)
        }
    }

    @Test
    fun `error-or-empty state should be raised when product refresh result is empty`() {
        runTest(coroutineScope.testDispatcher) {
// Given
            Mockito.`when`(mProductRepository.getAllProductsWithReFetchIfNeeded()).thenReturn(listOf())
            productViewModel = ProductsViewModel(mProductRepository, coroutineScope.testDispatcher)
// When
            productViewModel?.refreshProducts()
// then
// First state change should be loading
            val firstState = productViewModel?.productLoadStates?.getOrAwaitValue()

            Assert.assertTrue(firstState is ProductLoadStates.Loading)
// Second state change should be Error
            advanceUntilIdle()
// then
            val finalState = productViewModel?.productLoadStates?.getOrAwaitValue()
            Assert.assertTrue(finalState is ProductLoadStates.EmptyOrError)
        }
    }

    // State changes related
    // 1. Loading state is raised
    // 2. Error state is raised
    // 3. Loaded state is raised
    // 4. After refreshing correct states are raised
    // 5. After searching with a valid keyword, correct states are raised
    // 6. After searching with empty keyword, all products are loaded state is raised
    // 7. After searching wrong keyword, error state is raised
    // 8. Note: In viewmodel we should be testing, when user raises any events, the states are raised correctly

}