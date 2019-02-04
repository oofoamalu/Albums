package com.obiomaofoamalu.albums.data.local

import com.obiomaofoamalu.albums.core.model.Album
import com.obiomaofoamalu.albums.data.mapper.IAlbumMapper
import com.obiomaofoamalu.albums.data.room.dao.IAlbumDAO
import io.reactivex.Single
import javax.inject.Inject

/**
 * The [LocalAlbumRepository] is an implementation of the [ILocalAlbumRepository].
 *
 * @constructor Instantiates the [LocalAlbumRepository] object.
 * @param dao is the [IAlbumDAO].
 * @param mapper is the [IAlbumMapper].
 */
class LocalAlbumRepository @Inject constructor(
    private val mapper: IAlbumMapper,
    private val dao: IAlbumDAO
) : ILocalAlbumRepository {

    override fun saveAll(albums: Array<Album>): Single<Array<Long>> {
        return Single.fromCallable {
            dao.saveAll(mapper.bosToDtos(albums))
        }
    }

    override fun getAll(): Single<Array<Album>> {
        return dao.getAll()
            .map { mapper.dtosToBos(it) }
    }
}
