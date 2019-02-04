package com.obiomaofoamalu.albums.data.local

import com.obiomaofoamalu.albums.core.model.Album
import com.obiomaofoamalu.albums.data.mapper.IAlbumMapper
import com.obiomaofoamalu.albums.data.room.dao.IAlbumDAO
import com.obiomaofoamalu.albums.data.room.entity.AlbumDTO
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class LocalAlbumRepositoryTest {

    @Mock
    private lateinit var mapper: IAlbumMapper

    @Mock
    private lateinit var dao: IAlbumDAO

    private lateinit var repository: LocalAlbumRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = LocalAlbumRepository(mapper, dao)
    }

    @Test
    fun shouldSaveAlbumsInDatabase() {
        // GIVEN an array of albums
        val album = createAlbum()
        val albumDTO = createAlbumDTO(album)
        val albums = arrayOf(album)
        val albumDTOs = arrayOf(albumDTO)
        initMocksBehaviour(album, albums, albumDTO, albumDTOs)

        // WHEN we call repository.saveAll() and subscribe
        val testObserver = repository.saveAll(albums).test()

        // THEN verify that albums was mapped to albumDTOs
        verify(mapper).bosToDtos(albums)
        // THEN verify that mapped dtos was saved in the database
        verify(dao).saveAll(albumDTOs)
        // THEN verify that one item was saved in the database
        testObserver.assertValueCount(1)
    }

    @Test
    fun shouldReturnAllSavedAlbums() {
        // GIVEN an array of albums
        val album = createAlbum()
        val albumDTO = createAlbumDTO(album)
        val albums = arrayOf(album)
        val albumDTOs = arrayOf(albumDTO)
        initMocksBehaviour(album, albums, albumDTO, albumDTOs)

        // GIVEN that albums are saved in the database
        `when`(dao.getAll()).thenReturn(Single.just(albumDTOs))

        // WHEN we call repository.getAll() and subscribe
        val testObserver = repository.getAll().test()

        // THEN verify that data was retrieved was retrieved from database.
        verify(dao).getAll()
        // THEN verify that returned data was mapped to business object
        verify(mapper).dtosToBos(albumDTOs)
        // THEN verify that one item was emitted
        testObserver.assertValueCount(1)
        // THEN verify that emitted item was albums
        testObserver.assertValue(albums)
    }

    @Test
    fun shouldReturnEmptyArray_IfNoDataIsStoredInDatabase() {
        // GIVEN that database is empty
        val emptyAlbumArray = emptyArray<Album>()
        val emptyAlbumDTOArray = emptyArray<AlbumDTO>()
        `when`(dao.getAll()).thenReturn(Single.just(emptyAlbumDTOArray))
        `when`(mapper.dtosToBos(emptyAlbumDTOArray)).thenReturn(emptyAlbumArray)

        // WHEN we call repository.getAll() and subscribe
        val testObserver = repository.getAll().test()

        // THEN verify that no data was returned
        testObserver.assertValue(emptyAlbumArray)
    }

    private fun initMocksBehaviour(
        album: Album, albums: Array<Album>, albumDTO: AlbumDTO,
        albumDTOs: Array<AlbumDTO>
    ) {
        `when`(mapper.boTodto(album)).thenReturn(albumDTO)
        `when`(mapper.bosToDtos(albums)).thenReturn(albumDTOs)
        `when`(mapper.dtoToBo(albumDTO)).thenReturn(album)
        `when`(mapper.dtosToBos(albumDTOs)).thenReturn(albums)
        `when`(dao.saveAll(albumDTOs)).thenReturn(arrayOf(1))
    }

    private fun createAlbumDTO(album: Album): AlbumDTO =
        AlbumDTO(album.id, album.userId, album.title)

    private fun createAlbum(): Album = Album(1, 1, "album  name")
}
