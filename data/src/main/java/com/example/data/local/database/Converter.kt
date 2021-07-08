package com.example.data.local.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.io.Serializable

class Converters {
    @TypeConverter
    fun fromAuthorsList(value: List<String>): String {
        return value.joinToString { "," }
    }

    @TypeConverter
    fun toAuthorsList(value: String): List<String> {
        return value.split(",")
    }
}