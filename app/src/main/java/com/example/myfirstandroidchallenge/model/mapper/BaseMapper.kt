package com.example.myfirstandroidchallenge.model.mapper

/**
 * This interface is used to define the base mapper interface for all the mappers
 */
interface BaseMapper<in A, out B> {

    fun map(type: A?): B
}