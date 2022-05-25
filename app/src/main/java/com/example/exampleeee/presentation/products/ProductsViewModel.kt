package com.example.exampleeee.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.example.exampleeee.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
@ExperimentalPagingApi
class ProductsViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    val products = useCases.getProducts().cachedIn(viewModelScope)
}