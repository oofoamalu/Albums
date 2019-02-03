package com.obiomaofoamalu.albums.data.local

import com.obiomaofoamalu.albums.core.Album
import com.obiomaofoamalu.albums.data.mapper.IAlbumMapper
import com.obiomaofoamalu.albums.data.room.dao.IAlbumDAO
import io.reactivex.Single
import javax.inject.Inject

//TODO: kotlin doc
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
