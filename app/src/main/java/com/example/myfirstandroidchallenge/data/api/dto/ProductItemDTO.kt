package com.example.myfirstandroidchallenge.data.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Class to store the product item data
 */
data class ProductItemDTO(
    @JsonProperty("name") val name: String? = null,

    @JsonProperty("price") val price: String? = null,

    @JsonProperty("extra") val extra: String? = null,

    @JsonProperty("image") val image: String? = null,
)