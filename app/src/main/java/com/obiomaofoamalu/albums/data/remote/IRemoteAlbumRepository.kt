package com.obiomaofoamalu.albums.data.remote

import com.obiomaofoamalu.albums.core.Album
import io.reactivex.Observable

//TODO: kotlin doc
interface IRemoteAlbumRepository {

    //TODO: kotlin doc
    fun getAlbums(): Observable<Array<Album>>
}
