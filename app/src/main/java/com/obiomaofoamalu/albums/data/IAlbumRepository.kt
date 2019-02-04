package com.obiomaofoamalu.albums.data

import com.obiomaofoamalu.albums.core.model.Album
import io.reactivex.Single

/**
 * The [IAlbumRepository] interface defines methods used to store/retrieve [Album]s.
 */
interface IAlbumRepository {

    /**
     * @return the cold and finite [Single] array of [Album]s.
     */
    fun getAlbums(): Single<Array<Album>>

    /**
     * Saves the [Album] data.
     *
     * @param albums is an array of [Album]s.
     * @return the cold and finite [Single] array of [Long]s.
     */
    fun saveAlbums(albums: Array<Album>): Single<Array<Long>>
}
