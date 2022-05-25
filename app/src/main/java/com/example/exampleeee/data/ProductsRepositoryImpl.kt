package com.example.exampleeee.data

import com.example.exampleeee.domain.repository.ProductsRepository
import androidx.paging.Pager
import androidx.paging.PagingConfig
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class ProductsRepositoryImpl @Inject constructor(
    private val source: FirestorePagingSource,
    private val config: PagingConfig
): ProductsRepository {
    override fun getProducts()= Pager(
        config = config
    ) {
        source
    }.flow
}