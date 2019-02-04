package com.obiomaofoamalu.albums.data.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.obiomaofoamalu.albums.data.room.entity.AlbumDTO
import io.reactivex.Single

/**
 * The [IAlbumDAO] interface defines methods used for accessing [AlbumDTO] objects stored in the
 * database.
 */
@Dao
interface IAlbumDAO {

    /**
     * Bulk inserts the given data into the database.
     *
     * @param albumDTOs is the data to insert.
     * @return an array of rowIds affected by insertion.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(albumDTOs: Array<AlbumDTO>): Array<Long>

    /**
     * @return a cold and finite [Single] array of [AlbumDTO]s
     */
    @Query("SELECT * FROM Albums")
    fun getAll(): Single<Array<AlbumDTO>>
}
