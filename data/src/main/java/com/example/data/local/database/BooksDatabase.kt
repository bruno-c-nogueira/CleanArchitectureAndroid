package com.example.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.entity.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BooksDatabase : RoomDatabase() {
    abstract val dao: BooksDao
}