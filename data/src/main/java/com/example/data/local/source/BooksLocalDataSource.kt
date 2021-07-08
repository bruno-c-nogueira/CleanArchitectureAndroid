package com.example.data.local.source

import com.example.data.local.database.BooksDao
import com.example.data.local.mapper.BooksEntityMapper
import com.example.domain.entities.Volume
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface BooksLocalDataSource {
    suspend fun getBookmarks(author: String): Flow<List<Volume>>
    suspend fun bookmark(book: Volume)
    suspend fun unbookmark(book: Volume)
    suspend fun unbooksmark()
}

class BooksLocalDataSourceImpl(
    private val dao: BooksDao,
    private val bookEntityMapper: BooksEntityMapper
) : BooksLocalDataSource {
    override suspend fun getBookmarks(author: String): Flow<List<Volume>> {
        val savedBooksFlow = dao.findAll()
        return savedBooksFlow.map { list ->
            list.map { element ->
                bookEntityMapper.toVolume(element)
            }
        }
    }

    override suspend fun bookmark(book: Volume) {
        dao.saveBook(bookEntityMapper.toBookEntity(book))
    }

    override suspend fun unbookmark(book: Volume) {
        dao.deleteBook(bookEntityMapper.toBookEntity(book))
    }

    override suspend fun unbooksmark() {
        dao.deleteBooks()
    }

}