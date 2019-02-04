package com.obiomaofoamalu.albums.data.di

import android.arch.persistence.room.Room
import android.content.Context
import com.obiomaofoamalu.albums.data.AlbumRepository
import com.obiomaofoamalu.albums.data.IAlbumRepository
import com.obiomaofoamalu.albums.data.local.ILocalAlbumRepository
import com.obiomaofoamalu.albums.data.local.LocalAlbumRepository
import com.obiomaofoamalu.albums.data.mapper.AlbumMapper
import com.obiomaofoamalu.albums.data.mapper.IAlbumMapper
import com.obiomaofoamalu.albums.data.remote.IRemoteAlbumRepository
import com.obiomaofoamalu.albums.data.remote.RemoteAlbumRepository
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
    fun providesAlbumDao(appDatabase: AppDatabase): IAlbumDAO = appDatabase.getAlbumDAO()

    @Provides
    fun providesAlbumMapper(albumMapper: AlbumMapper): IAlbumMapper = albumMapper

    @Provides
    fun providesLocalAlbumRepository(
        localAlbumRepository: LocalAlbumRepository
    ): ILocalAlbumRepository = localAlbumRepository

    @Provides
    fun providesRemoteAlbumRepository(
        remoteAlbumRepository: RemoteAlbumRepository
    ): IRemoteAlbumRepository = remoteAlbumRepository

    @Provides
    fun providesAlbumRepository(albumRepository: AlbumRepository): IAlbumRepository =
        albumRepository
}
