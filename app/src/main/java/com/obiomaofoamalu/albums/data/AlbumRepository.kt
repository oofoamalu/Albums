package com.obiomaofoamalu.albums.data

import com.obiomaofoamalu.albums.core.Album
import com.obiomaofoamalu.albums.data.local.ILocalAlbumRepository
import com.obiomaofoamalu.albums.data.remote.IRemoteAlbumRepository
import io.reactivex.Single
import javax.inject.Inject

// TODO: kotlin doc
class AlbumRepository @Inject constructor(
    private val localAlbumRepository: ILocalAlbumRepository,
    private val remoteAlbumRepository: IRemoteAlbumRepository
) : IAlbumRepository {

    override fun getAlbums(): Single<Array<Album>> {
        return localAlbumRepository.getAll()
            .flatMap { check(it) }
    }

    override fun saveAlbums(albums: Array<Album>): Single<Array<Long>> =
        localAlbumRepository.saveAll(albums)

    private fun check(albums: Array<Album>): Single<Array<Album>> {
        return if (albums.isEmpty()) {
            remoteAlbumRepository.getAlbums()
                .singleOrError()
                .doOnSuccess { saveAlbums(it).subscribe() }
        } else {
            Single.just(albums)
        }
    }
}
