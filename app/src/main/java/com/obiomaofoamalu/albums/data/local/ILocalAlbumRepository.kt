package com.obiomaofoamalu.albums.data.local

import com.obiomaofoamalu.albums.core.Album
import io.reactivex.Single

//TODO: kotlin doc
interface ILocalAlbumRepository {

    //TODO: kotlin doc
    fun saveAll(albums: Array<Album>): Single<Array<Long>>

    //TODO: kotlin doc
    fun getAll(): Single<Array<Album>>
}
