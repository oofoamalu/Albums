package com.obiomaofoamalu.albums.data

import com.obiomaofoamalu.albums.core.model.Album
import com.obiomaofoamalu.albums.data.local.ILocalAlbumRepository
import com.obiomaofoamalu.albums.data.remote.IRemoteAlbumRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * The [AlbumRepository] is an implementation of the [IAlbumRepository].
 *
 * @constructor Instantiates the [AlbumRepository] object.
 * @param localAlbumRepository is the [ILocalAlbumRepository].
 * @param remoteAlbumRepository is the [IRemoteAlbumRepository].
 */
class AlbumRepository @Inject constructor(
    private val localAlbumRepository: ILocalAlbumRepository,
    private val remoteAlbumRepository: IRemoteAlbumRepository
) : IAlbumRepository {

    override fun getAlbums(): Single<Array<Album>> {
        return localAlbumRepository.getAll()
            .flatMap { mapOperator(it) }
    }

    override fun saveAlbums(albums: Array<Album>): Single<Array<Long>> =
        localAlbumRepository.saveAll(albums)

    private fun mapOperator(albums: Array<Album>): Single<Array<Album>> {
        return if (albums.isEmpty()) {
            remoteAlbumRepository.getAlbums()
                .singleOrError()
                .doOnSuccess { saveAlbums(it).subscribe() }
        } else {
            Single.just(albums)
        }
    }
}
