package com.example.tarotoneinviewmode.di

import android.content.Context
import com.example.tarotoneinviewmode.data.cards.TarotDeck
import com.example.tarotoneinviewmode.data.local.OraculoDatabase
import com.example.tarotoneinviewmode.data.preferences.OraculoPreferences
import com.example.tarotoneinviewmode.domain.model.TarotCard
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): OraculoDatabase {
        return OraculoDatabase.getInstance(context)
    }

    @Provides
    fun provideLeituraDao(db: OraculoDatabase) = db.leituraDao()

    @Provides
    fun providePraticaDao(db: OraculoDatabase) = db.praticaDao()

    @Provides
    fun provideFavoritoDao(db: OraculoDatabase) = db.favoritoDao()

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context): OraculoPreferences {
        return OraculoPreferences(context)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideTarotCards(): List<TarotCard> = TarotDeck
}
