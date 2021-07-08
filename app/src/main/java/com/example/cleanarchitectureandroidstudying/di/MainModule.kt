package com.example.cleanarchitectureandroidstudying.di

import com.example.cleanarchitectureandroidstudying.mappers.BookWithStatusMapper
import com.example.cleanarchitectureandroidstudying.presentation.BooksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        BooksViewModel(get(),get(),get(),get(),get())
    }
    factory { BookWithStatusMapper() }
}