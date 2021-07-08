package com.example.data.local.mapper

import com.example.data.local.entity.BookEntity
import com.example.domain.entities.Volume
import com.example.domain.entities.VolumeInfo

class BooksEntityMapper {
    fun toBookEntity(volume: Volume): BookEntity {
        val authors =  volume.volumeInfo.authors ?: emptyList<String>()
        return BookEntity(
            id = volume.id,
            title = volume.volumeInfo.title,
            authors = authors,
            imageUrl = volume.volumeInfo.imageUrl
        )
    }

    fun toVolume(bookEntity: BookEntity): Volume{
        return Volume(
            bookEntity.id,
            VolumeInfo(bookEntity.title, bookEntity.authors, bookEntity.imageUrl)
        )
    }
}