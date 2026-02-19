package com.example.flix.search.di

import com.example.flix.search.data.repository.SearchRepository
import com.example.flix.search.data.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchRepositoryModule {


    @Binds
    @ViewModelScoped
    abstract fun bindSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository
}