package com.obiomaofoamalu.albums.di

import com.obiomaofoamalu.albums.AlbumsApplication
import com.obiomaofoamalu.albums.network.di.NetworkModule
import dagger.Module

/**
 * The [MainApplicationModule] is used to provide app-wide dependencies.
 */
@Module(includes = [NetworkModule::class])
class MainApplicationModule(application: AlbumsApplication)
