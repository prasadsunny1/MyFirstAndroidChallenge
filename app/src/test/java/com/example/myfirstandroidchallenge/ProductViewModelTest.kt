package com.example.myfirstandroidchallenge

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myfirstandroidchallenge.repository.ProductDO
import com.example.myfirstandroidchallenge.repository.ProductRepository
import com.example.myfirstandroidchallenge.view_model.ProductLoadStates
import com.example.myfirstandroidchallenge.view_model.ProductsViewModel
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
    fun `Test weather searching keyword, test 4, returns only one result `() {

    }

    @Test
    fun `running get products should load all the products`() {
        Mockito.`when`(mProductRepository.getAllProductsWithReFetchIfNeeded())
            .thenReturn(listOf<ProductDO>())
        productViewModel = ProductsViewModel(mProductRepository, coroutineScope.testDispatcher)
        productViewModel?.onViewCreated()

        // First state change should be loading
        val result = productViewModel?.productLoadStates?.getOrAwaitValue()
        Assert.assertTrue(result is ProductLoadStates.Loading)
        // Second state change should be Error
        val resultState2 = productViewModel?.productLoadStates?.getOrAwaitValue()

        Assert.assertTrue(resultState2 is ProductLoadStates.EmptyOrError)
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