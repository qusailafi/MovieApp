package com.example.movie.domain.repo
import com.example.movie.utils.NetworkUtils
import com.example.movie.utils.Resource
import retrofit2.Response
abstract class BaseDataSource {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
// make a request on server and return a response
        if(NetworkUtils.isNetworkAvailable()){
            try {
                val response = apiCall()
                 if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        return Resource.success(body)
                    }else{
                        return Resource.none()
                    }
                }
                return Resource.error(data=null,"Something went wrong, try again later")
            } catch (e: Exception) {
                return Resource.error(data = null,"Something went wrong, $e")
            }
        }else{
            return Resource.networkError()
        }
    }

}