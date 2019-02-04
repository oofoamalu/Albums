package com.obiomaofoamalu.albums.data.remote

import com.obiomaofoamalu.albums.core.model.Album
import com.obiomaofoamalu.albums.network.AlbumsAPI
import io.reactivex.Observable
import javax.inject.Inject

/**
 * The [RemoteAlbumRepository] is an implementation of [IRemoteAlbumRepository].
 *
 * @constructor Instantiates the [RemoteAlbumRepository] object.
 * @param albumsAPI is the [AlbumsAPI].
 */
class RemoteAlbumRepository @Inject constructor(
    private val albumsAPI: AlbumsAPI
) : IRemoteAlbumRepository {

    override fun getAlbums(): Observable<Array<Album>> = albumsAPI.getAlbums()
}
