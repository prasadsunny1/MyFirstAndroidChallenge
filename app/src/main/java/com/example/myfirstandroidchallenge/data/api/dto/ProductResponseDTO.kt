package com.example.myfirstandroidchallenge.data.api.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Class to store the product response data
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class ProductResponseDTO(
    @JsonProperty("status") val status: String? = null,

    @JsonProperty("error") val error: Any? = null,

    @JsonProperty("data") val data: ProductsDataDTO? = null
)

