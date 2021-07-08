package com.example.data.local.database

import androidx.room.*
import com.example.data.local.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBook(book: BookEntity)

    @Query("SELECT * FROM book")
    fun findAll(): Flow<List<BookEntity>>

    @Delete
    suspend fun deleteBook(book: BookEntity)

    @Query("DELETE FROM book")
    suspend fun deleteBooks()
}