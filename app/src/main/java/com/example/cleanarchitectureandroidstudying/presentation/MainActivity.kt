package com.example.cleanarchitectureandroidstudying.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cleanarchitectureandroidstudying.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    val viewModel by viewModel<BooksViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getBooks("Robert Martin")
    }
}