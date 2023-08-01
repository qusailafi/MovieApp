package com.example.movie.utils
//Resource class its representation api status
data class Resource<out T> (val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> none(): Resource<T> = Resource(status = Status.NONE, data = null, message = null)

        fun <T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> = Resource(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> = Resource(status = Status.LOADING, data = data, message = null)


        fun <T> networkError (): Resource<T> = Resource(status = Status.NETWORK_ERROR, data = null, message = null)
    }
}