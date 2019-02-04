package com.obiomaofoamalu.albums.albumlist

import com.obiomaofoamalu.albums.albumlist.domain.AlbumListInteractor
import com.obiomaofoamalu.albums.core.domain.IAlbumRepository
import com.obiomaofoamalu.albums.core.model.Album
import io.reactivex.Single
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class AlbumListInteractorTest {

    @Mock
    private lateinit var repository: IAlbumRepository

    private lateinit var interactor: AlbumListInteractor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        interactor = AlbumListInteractor(repository)
    }

    @Test
    fun shouldReturnASortedListOfAlbumViewModel() {
        // GIVEN that we have albums data in the repository
        val albums = createAlbums()
        `when`(repository.getAlbums()).thenReturn(Single.just(albums))

        // WHEN we call interactor.getAlbums() and subscribe
        val testObserver = interactor.getAlbums().test()

        // THEN verify that emitted item is a list of AlbumViewModel
        testObserver.assertValue { it is List<AlbumViewModel> }
        // THEN verify that emitted list is sorted in ascending order
        val result = testObserver.values()[0]
        // assert that the first item in the list has title 'eight'
        assertTrue("eight" == result[0].title)
        // assert that the second item in the list has title 'first'
        assertTrue("first" == result[1].title)
        // assert that the third item in the list has title 'second'
        assertTrue("second" == result[2].title)
    }

    private fun createAlbums(): Array<Album> =
        arrayOf(
            Album(1, 1, "second"), Album(2, 1, "first"), Album(3, 2, "eight")
        )
}
