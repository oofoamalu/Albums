package com.obiomaofoamalu.albums.data.mapper

import com.obiomaofoamalu.albums.core.model.Album
import com.obiomaofoamalu.albums.data.room.entity.AlbumDTO
import javax.inject.Inject

/**
 * The [AlbumMapper] is an implementation of of the [IAlbumMapper].
 *
 * @constructor Instantiates the [AlbumMapper] object.
 */
class AlbumMapper @Inject constructor(
    // DI constructor
) : IAlbumMapper {

    override fun boTodto(bo: Album): AlbumDTO {
        return AlbumDTO(bo.id, bo.userId, bo.title)
    }

    override fun dtoToBo(dto: AlbumDTO): Album {
        return Album(dto.id, dto.userId, dto.title)
    }

    override fun bosToDtos(bos: Array<Album>): Array<AlbumDTO> {
        bos.let {
            val dtos = arrayOfNulls<AlbumDTO>(it.size)
            for (i in 0 until (it.size)) {
                dtos[i] = boTodto(it[i])
            }
            return dtos.requireNoNulls()
        }
    }

    override fun dtosToBos(dtos: Array<AlbumDTO>): Array<Album> {
        dtos.let {
            val bos = arrayOfNulls<Album>(it.size)
            for (i in 0 until (it.size)) {
                bos[i] = dtoToBo(it[i])
            }
            return bos.requireNoNulls()
        }
    }
}
