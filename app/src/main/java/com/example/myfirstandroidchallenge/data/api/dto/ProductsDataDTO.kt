package com.example.myfirstandroidchallenge.data.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Class to store the product response data
 */
data class ProductsDataDTO(
    @JsonProperty("items") val productItems: List<ProductItemDTO>? = null
)