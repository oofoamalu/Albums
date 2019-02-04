package com.obiomaofoamalu.albums.data.remote

import com.obiomaofoamalu.albums.core.model.Album
import io.reactivex.Observable

/**
 * The [IRemoteAlbumRepository] interface defines methods used to retrieve data from the server.
 */
interface IRemoteAlbumRepository {

    /**
     * @return the cold and finite [Observable] array of [Album].
     */
    fun getAlbums(): Observable<Array<Album>>
}
