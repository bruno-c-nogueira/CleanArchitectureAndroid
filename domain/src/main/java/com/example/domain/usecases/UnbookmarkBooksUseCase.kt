package com.example.domain.usecases

import com.example.domain.entities.Volume
import com.example.domain.repository.BooksRepository

class UnbookmarkBooksUseCase(private val booksRepository: BooksRepository) {
    suspend operator fun invoke(book: Volume) = booksRepository.unbookmark(book)
}