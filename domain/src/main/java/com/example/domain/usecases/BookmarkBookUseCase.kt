package com.example.domain.usecases

import com.example.domain.entities.Volume
import com.example.domain.repository.BooksRepository

class BookmarkBookUseCase(private val booksRepository: BooksRepository) {
    suspend operator fun invoke(book: Volume) = booksRepository.bookmark(book)
}