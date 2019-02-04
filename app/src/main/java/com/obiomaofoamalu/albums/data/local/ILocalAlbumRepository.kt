package com.obiomaofoamalu.albums.data.local

import com.obiomaofoamalu.albums.core.model.Album
import io.reactivex.Single

/**
 * The [ILocalAlbumRepository] interface defines methods for storing/retrieving data from the local
 * storage.
 */
interface ILocalAlbumRepository {

    /**
     * Stores the data into the local storage.
     *
     * @param albums is an array of [Album]s to save.
     * @return the cold and finite [Single] list of [Long].
     */
    fun saveAll(albums: Array<Album>): Single<Array<Long>>

    /**
     * @return the cold and finite [Single] array of [Album]s.
     */
    fun getAll(): Single<Array<Album>>
}
