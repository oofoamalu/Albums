package com.obiomaofoamalu.albums.data.mapper

import com.obiomaofoamalu.albums.core.Album
import com.obiomaofoamalu.albums.data.room.entity.AlbumDTO
import javax.inject.Inject

//TODO: kotlin doc
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
        val dtos = arrayOfNulls<AlbumDTO>(bos.size)
        for (i in 0 until (bos.size)) {
            dtos[i] = boTodto(bos[i])
        }
        return dtos.requireNoNulls()
    }

    override fun dtosToBos(dtos: Array<AlbumDTO>): Array<Album> {
        val bos = arrayOfNulls<Album>(dtos.size)
        for (i in 0 until (dtos.size)) {
            bos[i] = dtoToBo(dtos[i])
        }
        return bos.requireNoNulls()
    }
}
