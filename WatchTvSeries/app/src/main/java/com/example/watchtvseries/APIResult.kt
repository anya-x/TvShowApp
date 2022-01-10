package com.example.watchtvseries

sealed class APIResult<T> ( val data: T? = null,
                            val msg: String? = null){

    class Load<T>: APIResult<T>()
    class Success<T>(data: T): APIResult<T>(data)
    class Failure<T>(msg: String?, data: T? = null): APIResult<T>(data, msg)

}