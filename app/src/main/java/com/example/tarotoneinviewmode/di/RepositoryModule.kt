package com.example.tarotoneinviewmode.di

import com.example.tarotoneinviewmode.data.repository.InMemoryTarotRepository
import com.example.tarotoneinviewmode.data.repository.TarotRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTarotRepository(
        impl: InMemoryTarotRepository
    ): TarotRepository
}
