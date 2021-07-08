package com.example.domain.repository

import com.example.domain.Result
import com.example.domain.entities.Volume
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    suspend fun getRemoteBooks(author: String): Result<List<Volume>>

    suspend fun getBookmarks(author: String): Flow<Result<List<Volume>>>

    suspend fun bookmark(book: Volume)

    suspend fun unbookmark(book: Volume)
}