package com.example.cleanarchitectureandroidstudying.presentation


import com.example.cleanarchitectureandroidstudying.entities.BookWithStatus
import com.example.cleanarchitectureandroidstudying.mappers.BookWithStatusMapper
import com.example.domain.entities.Volume
import com.example.domain.usecases.*
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.*
import com.example.domain.Result
import kotlinx.coroutines.flow.collect

class BooksViewModel(
    private val getBooksUseCase: GetBookUseCase,
    private val getBookmarksUseCase: GetBookmarksUseCase,
    private val bookmarkBookUseCase: BookmarkBookUseCase,
    private val unbookmarkBooksUseCase: UnbookmarkBooksUseCase,
    private val mapper: BookWithStatusMapper
) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _books = MutableLiveData<List<BookWithStatus>>()
    val books = _books

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _remoteBooks = arrayListOf<Volume>()

    // Getting books with uncle bob as default author :)
    fun getBooks(author: String) {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            val bookmarksFlow = getBookmarksUseCase.invoke(author)
            bookmarksFlow.collect { bookmarks ->
                if (bookmarks is Result.Success) {
                    if (bookmarks.data.isNullOrEmpty()) {
                        requestBooksInApi(author)
                    } else {
                        books.value =
                            mapper.fromVolumeToBookWithStatus(_remoteBooks, bookmarks.data)
                        _dataLoading.postValue(false)
                    }

                } else if (bookmarks is Result.Error) {
                    _error.postValue(bookmarks.exception.localizedMessage)
                    requestBooksInApi(author)
                }

            }

        }
    }

    suspend fun requestBooksInApi(author: String) {
        when (val booksResult = getBooksUseCase.invoke(author)) {
            is Result.Success -> {
                _remoteBooks.clear()
                _remoteBooks.addAll(booksResult.data)
                books.value = mapper.fromVolumeToBookWithStatus(_remoteBooks, booksResult.data)

                books.value?.forEach {
                    bookmark(it)
                }
            }

            is Result.Error -> {
                _dataLoading.postValue(false)
                books.value = emptyList()
                _error.postValue(booksResult.exception.message)
            }
        }
    }

    fun bookmark(book: BookWithStatus) {
        viewModelScope.launch {
            bookmarkBookUseCase.invoke(mapper.fromBookWithStatusToVolume(book))
        }
    }

    fun unbookmark(book: BookWithStatus) {
        viewModelScope.launch {
            unbookmarkBooksUseCase.invoke(mapper.fromBookWithStatusToVolume(book))
        }
    }

    class BooksViewModelFactory(
        private val getBooksUseCase: GetBookUseCase,
        private val getBookmarksUseCase: GetBookmarksUseCase,
        private val bookmarkBookUseCase: BookmarkBookUseCase,
        private val unbookmarkBooksUseCase: UnbookmarkBooksUseCase,
        private val mapper: BookWithStatusMapper
    ) :
        ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return BooksViewModel(
                getBooksUseCase,
                getBookmarksUseCase,
                bookmarkBookUseCase,
                unbookmarkBooksUseCase,
                mapper
            ) as T
        }
    }
}