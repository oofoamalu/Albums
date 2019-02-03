package com.obiomaofoamalu.albums.data

import com.obiomaofoamalu.albums.core.Album
import io.reactivex.Single

//TODO; kotlin doc
interface IAlbumRepository {

    fun getAlbums(): Single<Array<Album>>

    fun saveAlbums(albums: Array<Album>): Single<Array<Long>>
}
