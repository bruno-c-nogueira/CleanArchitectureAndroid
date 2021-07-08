package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.BooksRepositoryImpl
import com.example.data.local.database.BooksDao
import com.example.data.local.database.BooksDatabase
import com.example.data.local.mapper.BooksEntityMapper
import com.example.data.local.source.BooksLocalDataSource
import com.example.data.local.source.BooksLocalDataSourceImpl
import com.example.data.remote.BooksRemoteDataSource
import com.example.data.remote.BooksRemoteDataSourceImpl
import com.example.data.remote.mapper.BooksResponseMapper
import com.example.domain.repository.BooksRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single<BooksRepository> { BooksRepositoryImpl(get(), get()) }
    single<BooksLocalDataSource> { BooksLocalDataSourceImpl(get(), get()) }
    single<BooksRemoteDataSource> { BooksRemoteDataSourceImpl(get()) }
    single { BooksResponseMapper() }
    factory { BooksEntityMapper() }

}

val databaseModule = module {
    fun provideDatabase(application: Application): BooksDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            BooksDatabase::class.java, "database-books"
        ).build()
    }

    fun providWizardDao(database: BooksDatabase): BooksDao {
        return database.dao
    }

    single { provideDatabase(androidApplication()) }
    single { providWizardDao(get()) }

}