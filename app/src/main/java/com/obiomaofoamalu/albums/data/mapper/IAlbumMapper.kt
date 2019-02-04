package com.obiomaofoamalu.albums.data.mapper

import com.obiomaofoamalu.albums.core.model.Album
import com.obiomaofoamalu.albums.core.mapper.IModelMapper
import com.obiomaofoamalu.albums.data.room.entity.AlbumDTO

/**
 * The [IAlbumMapper] transforms the Business Object [Album] into the Data Transfer Object [AlbumDTO]
 * and vice versa.
 */
interface IAlbumMapper : IModelMapper<Album, AlbumDTO>
