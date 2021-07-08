package com.example.data

import com.example.data.local.source.BooksLocalDataSource
import com.example.data.remote.BooksRemoteDataSource
import com.example.domain.Result
import com.example.domain.entities.Volume
import com.example.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BooksRepositoryImpl(
    private val localDataSource: BooksLocalDataSource,
    private val remoteDataSource: BooksRemoteDataSource
) : BooksRepository {
    override suspend fun getRemoteBooks(author: String): Result<List<Volume>> {
        return remoteDataSource.getBooks(author)
    }

    override suspend fun getBookmarks(author: String): Flow<Result<List<Volume>>> {
        return localDataSource.getBookmarks(author)
            .map { cacheList ->
                val result = when {
                    cacheList.isEmpty() -> getRemoteBooks(author)
                    else -> {
                        Result.Success(cacheList)

                    }
                }

                result
            }
    }

    override suspend fun bookmark(book: Volume) {
        localDataSource.bookmark(book)
    }

    override suspend fun unbookmark(book: Volume) {
        localDataSource.unbookmark(book)
    }
}