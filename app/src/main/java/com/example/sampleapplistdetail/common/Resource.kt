package com.example.sampleapplistdetail.common

sealed class Resource<Any>(val data: Any? = null, val message: String? = null) {

    class Success<Any>(data: Any) : Resource<Any>(data)

    class Error<Any>(message: String) : Resource<Any>(null, message)

    class Loading<Any>() : Resource<Any>(null)
}