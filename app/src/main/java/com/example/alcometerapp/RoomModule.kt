package com.example.alcometerapp

import android.content.Context
import androidx.room.Room
import com.example.alcometerapp.ui.profile.ProfileDao
import com.example.alcometerapp.ui.promiles.ResultDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): Database {
        return Room.databaseBuilder(
            appContext,
            Database::class.java,
            Database.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideProfileDao(Database: Database): ProfileDao {
        return Database.profileDao()
    }

    @Provides
    @Singleton
    fun provideResultDao(Database: Database): ResultDao {
        return Database.resultDao()
    }
}