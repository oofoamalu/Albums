package com.obiomaofoamalu.albums.core.mapper

/**
 * The [IModelMapper] interface defines methods to transform Business Object to Data Transfer Object
 * and vice versa.
 *
 * @param S represents the Business Object.
 * @param T represents the Data Transfer Object.
 */
interface IModelMapper<S, T> {

    /**
     * Transform the Business Object to the Data Transfer Object.
     *
     * @param bo [S] is the Business Object.
     * @return dto [T] is the Data Transfer Object.
     */
    fun boTodto(bo: S): T

    /**
     * Transform the Data Transfer Object to the Business Object.
     *
     * @param dto [T] is the Data Transfer Object.
     * @return bo [S] is the Business Object.
     */
    fun dtoToBo(dto: T): S

    /**
     * Transforms a collection of Business Objects into a collection of Data Transfer Objects.
     *
     * @param bos is an array of business objects.
     * @return an array of data transfer objects.
     */
    fun bosToDtos(bos: Array<S>): Array<T>

    /**
     * Transforms a collection of Data Transfer Objects into a collection of Business Objects.
     *
     * @param dtos is an array of data transfer objects.
     * @return an array of business objects.
     */
    fun dtosToBos(dtos: Array<T>): Array<S>
}
