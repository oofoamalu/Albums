package com.obiomaofoamalu.albums.albumlist.domain

import com.obiomaofoamalu.albums.albumlist.AlbumViewModel
import io.reactivex.Single

/**
 * The [IAlbumListInteractor] is a Business Logic Entity.
 * It is responsible for retrieving the [AlbumViewModel] data.
 */
interface IAlbumListInteractor {

    /**
     * @return the cold and finite [Single] list of [AlbumViewModel].
     */
    fun getAlbums(): Single<List<AlbumViewModel>>
}
