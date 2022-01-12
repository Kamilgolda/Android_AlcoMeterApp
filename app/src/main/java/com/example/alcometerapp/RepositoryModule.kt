package com.example.alcometerapp

import com.example.alcometerapp.ui.profile.ProfileDao
import com.example.alcometerapp.ui.promiles.ResultDao
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