package com.example.domain.usecases

import com.example.domain.repository.BooksRepository


class GetBookmarksUseCase(private val booksRepository: BooksRepository) {
    suspend operator fun invoke(author: String) = booksRepository.getBookmarks(author)
}