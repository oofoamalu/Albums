package com.obiomaofoamalu.albums.data.remote

import com.obiomaofoamalu.albums.core.model.Album
import com.obiomaofoamalu.albums.network.AlbumsAPI
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class RemoteAlbumRepositoryTest {

    @Mock
    private lateinit var albumsAPI: AlbumsAPI

    private lateinit var repository: RemoteAlbumRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = RemoteAlbumRepository(albumsAPI)
    }

    @Test
    fun shouldRetrieveAlbumsFromServer() {
        // GIVEN some data stored in the server
        `when`(albumsAPI.getAlbums()).thenReturn(Observable.just(arrayOf(createAlbum())))

        // WHEN we call repository.getAlbums() and subscribe
        repository.getAlbums().test()

        // THEN verify that data was retrieved from server
        verify(albumsAPI).getAlbums()
    }

    private fun createAlbum(): Album = Album(1, 1, "album  name")
}
