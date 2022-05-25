package com.example.exampleeee.domain.use_case

import com.example.exampleeee.domain.repository.ProductsRepository

class GetProducts(
    private val repository: ProductsRepository
) {
    operator fun invoke() = repository.getProducts()
}