package com.example.myfirstandroidchallenge.data.api.dto

import com.example.myfirstandroidchallenge.data.api.dto.ProductItemDTO
import com.fasterxml.jackson.annotation.JsonProperty

data class ProductsDataDTO(
    @JsonProperty("items") val productItems: List<ProductItemDTO>? = null
)