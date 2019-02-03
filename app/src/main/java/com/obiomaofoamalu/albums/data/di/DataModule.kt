package com.obiomaofoamalu.albums.data.di

import android.arch.persistence.room.Room
import android.content.Context
import com.obiomaofoamalu.albums.data.room.AppDatabase
import com.obiomaofoamalu.albums.data.room.dao.IAlbumDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * The [DataModule] class provides dependencies relating to data manipulation.
 */
@Module
class DataModule {

    @Provides
    @Singleton
    fun providesApplicationDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesAlbumDao(appDatabase: AppDatabase): IAlbumDAO = appDatabase.getAlbumDAO()
}
