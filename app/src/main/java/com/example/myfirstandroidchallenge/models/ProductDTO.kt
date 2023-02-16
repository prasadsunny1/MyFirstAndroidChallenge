package com.example.myfirstandroidchallenge.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
data class ProductDTO(
    @JsonProperty("status") val status: String? = null,
    @JsonProperty("error") val error: Any? = null,
    @JsonProperty("data") val data: Data? = null
)

data class Data(
    @JsonProperty("items") val productItems: List<ProductItem>? = null
)

data class ProductItem(
    @JsonProperty("name") val name: String? = null,

    @JsonProperty("price") val price: String? = null,

    @JsonProperty("extra") val extra: String? = null,

    @JsonProperty("image") val image: String? = null,
)
