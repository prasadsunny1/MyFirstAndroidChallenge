package com.example.myfirstandroidchallenge.repository

interface IProductRepository {

    suspend fun getProducts(): List<ProductDO>
}