package com.example.data.remote

import com.example.data.remote.mapper.BooksResponseMapper
import com.example.domain.entities.Volume
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit.ApiService
import  com.example.domain.Result
import java.lang.Exception


interface BooksRemoteDataSource {
    suspend fun getBooks(author: String): Result<List<Volume>>

}

class BooksRemoteDataSourceImpl(
    val mapper: BooksResponseMapper
) : BooksRemoteDataSource {
    override suspend fun getBooks(author: String): Result<List<Volume>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = ApiService.booksApi.getBooks(author)
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.toVolume(response.body()!!))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
    }


}