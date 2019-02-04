package com.obiomaofoamalu.albums.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.obiomaofoamalu.albums.data.room.dao.IAlbumDAO
import com.obiomaofoamalu.albums.data.room.entity.AlbumDTO

/**
 * The [AppDatabase] is the class definition for the application database.
 */
@Database(entities = [AlbumDTO::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    /**
     * @return the [IAlbumDAO].
     */
    abstract fun getAlbumDAO(): IAlbumDAO

    companion object {
        val NAME: String = AppDatabase::class.java.simpleName
    }
}
