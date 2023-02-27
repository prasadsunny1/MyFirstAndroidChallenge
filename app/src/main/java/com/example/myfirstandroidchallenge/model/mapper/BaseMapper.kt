package com.example.myfirstandroidchallenge.model.mapper

interface BaseMapper<in A, out B> {

    fun map(type: A?): B
}