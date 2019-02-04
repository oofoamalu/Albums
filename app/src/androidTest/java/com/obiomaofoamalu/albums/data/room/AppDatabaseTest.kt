package com.obiomaofoamalu.albums.data.room

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.obiomaofoamalu.albums.data.room.dao.IAlbumDAO
import com.obiomaofoamalu.albums.data.room.entity.AlbumDTO
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var albumDAO: IAlbumDAO
    private lateinit var testDatabase: AppDatabase

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getTargetContext()
        testDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        albumDAO = testDatabase.getAlbumDAO()
    }

    @After
    fun tearDown() {
        testDatabase.close()
    }

    @Test
    fun shouldSaveAndReadData() {
        // GIVEN data to save in database
        val albumDTOs = arrayOf(
            AlbumDTO(1, 1, "first album"),
            AlbumDTO(2, 1, "second album")
        )

        // WHEN we call saveAll() passing albumDTOs as argument
        val rows: Array<Long> = albumDAO.saveAll(albumDTOs)

        // THEN assert that two item were saved
        assertEquals(2, rows.size)

        // WHEN we call getAll()
        val result = albumDAO.getAll().blockingGet()

        // THEN assert that returned data is albumDTOs
        assertEquals(albumDTOs, result)
        // THEN assert that 2 items we returned
        assertEquals(2, result.size)
    }
}
