package com.example.data.remote.entity

import com.google.gson.annotations.SerializedName


class BooksApiResponse(val items: List<Item>)

data class Item(
    @SerializedName("id")
    val id: String,
    @SerializedName("volumeInfo")
    val volumeInfo: ApiVolumeInfo
)

data class ApiVolumeInfo(
    val title: String,
    val authors: List<String>?,
    val imageLinks: ImageLinks?
)

data class ImageLinks(val smallThumbnail: String, val thumbnail: String)