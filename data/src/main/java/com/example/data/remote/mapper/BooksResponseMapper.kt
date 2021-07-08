package com.example.data.remote.mapper

import com.example.data.remote.entity.BooksApiResponse
import com.example.domain.entities.Volume
import com.example.domain.entities.VolumeInfo

class BooksResponseMapper {

    fun toVolume(booksApi: BooksApiResponse): List<Volume> {
        return booksApi.items.map { mappedItem ->
            Volume(
                id = mappedItem.id,
                volumeInfo = VolumeInfo(
                    mappedItem.volumeInfo.title,
                    mappedItem.volumeInfo.authors,
                    mappedItem.volumeInfo.imageLinks?.thumbnail?.replace("http", "https"),
                )
            )
        }
    }
}