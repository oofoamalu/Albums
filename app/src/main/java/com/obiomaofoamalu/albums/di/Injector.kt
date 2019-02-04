package com.obiomaofoamalu.albums.di

import com.obiomaofoamalu.albums.AlbumsApplication

/**
 * The [Injector] initializes the [MainApplicationComponent] and
 * provides a convenient method for injection.
 */
class Injector {

    companion object {

        lateinit var applicationComponent: MainApplicationComponent

        /**
         * Initializes the [MainApplicationComponent].
         */
        fun createApplicationComponent(application: AlbumsApplication) {
            applicationComponent = DaggerMainApplicationComponent.builder()
                .mainApplicationModule(MainApplicationModule(application))
                .build()
        }
    }
}
