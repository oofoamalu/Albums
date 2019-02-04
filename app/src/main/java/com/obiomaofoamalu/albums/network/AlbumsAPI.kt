package com.obiomaofoamalu.albums.network

import com.obiomaofoamalu.albums.core.model.Album
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The [AlbumsAPI] interface defines methods to retrieve data from the server
 */
interface AlbumsAPI {

    @GET("albums")
    fun getAlbums(): Observable<Array<Album>>
}
