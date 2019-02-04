package com.obiomaofoamalu.albums.data

import com.obiomaofoamalu.albums.core.model.Album
import com.obiomaofoamalu.albums.data.local.ILocalAlbumRepository
import com.obiomaofoamalu.albums.data.remote.IRemoteAlbumRepository
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class AlbumRepositoryTest {

    @Mock
    private lateinit var localRepository: ILocalAlbumRepository

    @Mock
    private lateinit var remoteRepository: IRemoteAlbumRepository

    lateinit var repository: AlbumRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = AlbumRepository(localRepository, remoteRepository)
    }

    @Test
    fun shouldSaveAlbumsInLocalRepository() {
        // GIVEN an array of albums
        val albums = arrayOf(createAlbum())
        initMockBehaviour(albums)

        // WHEN  we call repository.saveAlbums(albums) and subscribe
        val testObserver = repository.saveAlbums(albums).test()

        // THEN verify that albums was saved locally
        verify(localRepository).saveAll(albums)
        // THEN verify that items were saved successfully
        testObserver.assertValueCount(1)
    }

    @Test
    fun shouldRetrieveAlbumsFromLocalRepository() {
        // GIVEN that albums are saved in the local repository
        val albums = arrayOf(createAlbum())
        `when`(localRepository.getAll()).thenReturn(Single.just(albums))

        // WHEN we call repository.getAlbums() and subscribe
        val testObserver = repository.getAlbums().test()

        // THEN verify that data was retrieved from local repository
        verify(localRepository).getAll()
        verify(remoteRepository, never()).getAlbums()
        // THEN verify that one item was returned
        testObserver.assertValueCount(1)
        // THEN verify that returned item was albums
        testObserver.assertValue(albums)
    }

    @Test
    fun shouldRetrieveAlbumsFromRemoteRepository() {
        // GIVEN that no data is saved in the local repository
        `when`(localRepository.getAll()).thenReturn(Single.just(emptyArray()))
        // GIVEN that data exits in the remote repository
        val albums = arrayOf(createAlbum())
        `when`(remoteRepository.getAlbums()).thenReturn(Observable.just(albums))

        // WHEN we call repository.getAlbums() and subscribe
        val testObserver = repository.getAlbums().test()

        // THEN verify that data was retrieved from remote repository
        verify(remoteRepository).getAlbums()
        // THEN verify that data was saved in the local repository
        verify(localRepository).saveAll(albums)
    }

    private fun initMockBehaviour(albums: Array<Album>) {
        `when`(localRepository.saveAll(albums)).thenReturn(Single.just(arrayOf(1L)))
    }

    private fun createAlbum(): Album = Album(1, 1, "album  name")
}
