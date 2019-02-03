package com.obiomaofoamalu.albums.di

import com.obiomaofoamalu.albums.AlbumListActivity
import dagger.Component
import javax.inject.Singleton

/**
 * The [MainApplicationComponent] is a Dagger DI component.
 */
@Singleton
@Component(modules = [MainApplicationModule::class])
interface MainApplicationComponent {

    fun inject(activity: AlbumListActivity)
}
