package com.obiomaofoamalu.albums.data.remote

import com.obiomaofoamalu.albums.core.Album
import com.obiomaofoamalu.albums.network.AlbumsAPI
import io.reactivex.Observable
import javax.inject.Inject

//TODO: kotlin doc
class RemoteAlbumRepository @Inject constructor(
    private val albumsAPI: AlbumsAPI
) : IRemoteAlbumRepository {

    override fun getAlbums(): Observable<Array<Album>> = albumsAPI.getAlbums()
}
