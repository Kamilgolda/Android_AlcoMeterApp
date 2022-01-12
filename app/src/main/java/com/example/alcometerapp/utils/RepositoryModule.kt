package com.example.alcometerapp.utils

import com.example.alcometerapp.database.ProfileDao
import com.example.alcometerapp.database.ResultDao
import com.example.alcometerapp.ui.viewmodel.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(profileDao: ProfileDao, resultDao: ResultDao) : Repository {
        return  Repository(profileDao, resultDao);
    }
}