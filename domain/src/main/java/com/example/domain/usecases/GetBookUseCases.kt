package com.example.domain.usecases

import com.example.domain.repository.BooksRepository

class GetBookUseCase(private val repository: BooksRepository) {
    suspend operator fun invoke(author: String) = repository.getRemoteBooks(author)
}