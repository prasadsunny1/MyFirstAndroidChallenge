package com.example.myfirstandroidchallenge.repository

import com.example.myfirstandroidchallenge.core.BaseMapper
import com.example.myfirstandroidchallenge.data_sources.database.ProductEntity
import com.example.myfirstandroidchallenge.data_sources.network.ProductItem

/**
 * Mapper to convert from API DTO to Domain Object
 * To be used when moving data from the API(Data source) layer to the domain layer
 */
object ApiDTOtoDO : BaseMapper<ProductItem, ProductDO> {
    override fun map(type: ProductItem?): ProductDO {
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

