package com.example.domain.di

import com.example.domain.usecases.BookmarkBookUseCase
import com.example.domain.usecases.GetBookUseCase
import com.example.domain.usecases.GetBookmarksUseCase
import com.example.domain.usecases.UnbookmarkBooksUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { BookmarkBookUseCase(get()) }
    factory { GetBookmarksUseCase(get()) }
    factory { GetBookUseCase(get()) }
    factory { UnbookmarkBooksUseCase(get()) }
}