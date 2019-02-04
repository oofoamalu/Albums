package com.obiomaofoamalu.albums.di

import android.content.Context
import com.obiomaofoamalu.albums.AlbumsApplication
import com.obiomaofoamalu.albums.albumlist.domain.AlbumListInteractor
import com.obiomaofoamalu.albums.albumlist.domain.IAlbumListInteractor
import com.obiomaofoamalu.albums.data.di.DataModule
import com.obiomaofoamalu.albums.network.di.NetworkModule
import dagger.Module
import dagger.Provides

/**
 * The [MainApplicationModule] is used to provide app-wide dependencies.
 */
@Module(includes = [NetworkModule::class, DataModule::class])
class MainApplicationModule(private val application: AlbumsApplication) {

    @Provides
    fun providesContext(): Context = application.applicationContext

    @Provides
    fun providesAlbumListInteractor(interactor: AlbumListInteractor): IAlbumListInteractor =
            interactor
}
