package com.obiomaofoamalu.albums

import android.app.Application
import com.obiomaofoamalu.albums.di.Injector

/**
 *  The [AlbumsApplication] is the custom [Application] class for this project.
 *  It initializes the Dagger application component.
 */
class AlbumsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.createApplicationComponent(this)
    }
}
