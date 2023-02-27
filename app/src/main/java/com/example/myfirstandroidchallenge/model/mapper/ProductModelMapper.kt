package com.example.myfirstandroidchallenge.data.repository

import com.example.myfirstandroidchallenge.model.mapper.BaseMapper
import com.example.myfirstandroidchallenge.data.databse.entity.ProductEntity
import com.example.myfirstandroidchallenge.data.api.dto.ProductItemDTO
import com.example.myfirstandroidchallenge.model.ProductDO

/**
 * Mapper to convert from API DTO to Domain Object
 * To be used when moving data from the API(Data source) layer to the domain layer
 */
object ApiDTOtoDO : BaseMapper<ProductItemDTO, ProductDO> {

    override fun map(type: ProductItemDTO?): ProductDO {
        return ProductDO(
            price = type?.price ?: "",
            name = type?.name ?: "",
            extra = type?.extra ?: "",
            image = type?.image ?: ""
        )
    }
}

/**
 * Mapper to convert DB Entity to Domain Object
 * To be used when moving data from the DB(Data source) layer to the domain layer
 */
object DbEntityToDo : BaseMapper<ProductEntity, ProductDO> {

    override fun map(type: ProductEntity?): ProductDO {
        return ProductDO(
            price = type?.price ?: "",
            name = type?.name ?: "",
            extra = type?.extra ?: "",
            image = type?.image ?: ""
        )
    }
}

/**
 * Mapper to convert Domain Object to DB Entity
 * To be used when moving data from the domain layer to the DB(Data source) layer
 */
object DoToDbEntity : BaseMapper<ProductDO, ProductEntity> {

    override fun map(type: ProductDO?): ProductEntity {
        return ProductEntity(
            price = type?.price ?: "",
            name = type?.name ?: "",
            extra = type?.extra ?: "",
            image = type?.image ?: "",
            id = type?.hashCode() ?: 0,
            timestamp = System.currentTimeMillis()
        )
    }
}

object ApiDtoToDBEntity : BaseMapper<ProductItemDTO, ProductEntity> {

    override fun map(type: ProductItemDTO?): ProductEntity {
        return ProductEntity(
            price = type?.price ?: "",
            name = type?.name ?: "",
            extra = type?.extra ?: "",
            image = type?.image ?: "",
            id = type?.hashCode() ?: 0,
        )
    }
}

