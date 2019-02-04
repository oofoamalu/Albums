package com.obiomaofoamalu.albums.data.room.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * The [AlbumDTO] is a Data Transfer Object container used to persist data in local Storage.
 * for Room database, this class represents a table.
 */
@Entity(tableName = "Albums")
data class AlbumDTO(@PrimaryKey val id: Int, val userId: Int, val title: String)
