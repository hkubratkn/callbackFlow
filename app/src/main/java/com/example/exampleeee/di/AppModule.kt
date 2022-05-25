package com.example.exampleeee.di

import androidx.paging.PagingConfig
import com.example.exampleeee.core.Constants.NAME
import com.example.exampleeee.core.Constants.PAGE_SIZE
import com.example.exampleeee.core.Constants.PRODUCTS
import com.example.exampleeee.data.FirestorePagingSource
import com.example.exampleeee.data.ProductsRepositoryImpl
import com.example.exampleeee.domain.repository.ProductsRepository
import com.example.exampleeee.domain.use_case.GetProducts
import com.example.exampleeee.domain.use_case.UseCases
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Query.Direction.ASCENDING
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideQueryProductsByName() = Firebase.firestore
        .collection(PRODUCTS)
        .orderBy(NAME, ASCENDING)
        .limit(PAGE_SIZE)

    @Provides
    fun provideFirestorePagingSource(
        queryProductsByName: Query
    ) = FirestorePagingSource(queryProductsByName)

    @Provides
    fun providePagingConfig() = PagingConfig(
        pageSize = PAGE_SIZE.toInt()
    )

    @Provides
    fun provideProductsRepository(
        source: FirestorePagingSource,
        config: PagingConfig
    ): ProductsRepository = ProductsRepositoryImpl(source, config)

    @Provides
    fun provideUseCases(repository: ProductsRepository) = UseCases(
        getProducts = GetProducts(repository)
    )
}