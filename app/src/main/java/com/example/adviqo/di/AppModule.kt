package com.example.adviqo.di

import android.content.Context
import androidx.room.Room
import com.example.adviqo.application.Common.Companion.DATABASE_NAME
import com.example.adviqo.data.local.ProductDao
import com.example.adviqo.data.local.ProductDatabase
import com.example.adviqo.data.remote.api.ApiService
import com.example.adviqo.data.repository.ProductRepositoryImplementation
import com.example.adviqo.domain.repository.ProductRepositoryDomain
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesProductDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ProductDatabase::class.java, DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun providesProductDao(
        database: ProductDatabase
    ) = database.productDao()


    @Singleton
    @Provides
    fun provideFilm(
        apiService: ApiService,
        dao: ProductDao
    ) = ProductRepositoryImplementation(
        api = apiService,
        dao = dao
    ) as ProductRepositoryDomain
}

