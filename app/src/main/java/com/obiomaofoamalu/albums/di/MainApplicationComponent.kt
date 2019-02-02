package com.obiomaofoamalu.albums.di

import dagger.Component
import javax.inject.Singleton

/**
 * The [MainApplicationComponent] is a Dagger DI component.
 */
@Singleton
@Component(modules = [MainApplicationModule::class])
interface MainApplicationComponent
