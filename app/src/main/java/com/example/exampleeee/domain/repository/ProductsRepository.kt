package com.example.exampleeee.domain.repository

import androidx.paging.PagingData
import com.example.exampleeee.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getProducts(): Flow<PagingData<Product>>
}