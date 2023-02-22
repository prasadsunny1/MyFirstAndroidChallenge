package com.example.myfirstandroidchallenge.core

interface BaseMapper<in A, out B> {

    fun map(type: A?): B
}