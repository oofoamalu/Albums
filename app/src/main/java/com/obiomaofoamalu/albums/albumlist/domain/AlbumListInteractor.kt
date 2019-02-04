package com.obiomaofoamalu.albums.albumlist.domain

import com.obiomaofoamalu.albums.albumlist.AlbumViewModel
import com.obiomaofoamalu.albums.core.domain.IAlbumRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * The [AlbumListInteractor] is an implementation of the [IAlbumListInteractor]
 */
class AlbumListInteractor @Inject constructor(
    private val albumRepository: IAlbumRepository
) : IAlbumListInteractor {

    override fun getAlbums(): Single<List<AlbumViewModel>> {
        return albumRepository.getAlbums()
            .flattenAsObservable { it.asList() }
            .map { it -> AlbumViewModel(it.title) }
            .toList()
            .map { albums -> albums.sortedBy { it.title } }
    }
}
