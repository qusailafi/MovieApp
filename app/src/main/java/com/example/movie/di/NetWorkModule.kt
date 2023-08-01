package com.example.movie.di

import com.example.movie.BuildConfig
import com.example.movie.data.remote.ApiEndPoints
import com.example.movie.data.repo.RepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//NetWorkModule used to make hilt provide us instance form Retrofit library and  HttpClient.
@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(Interceptor {
                val original: Request = it.request()

                val originalHttpUrl: HttpUrl = it.request().url
                var url: HttpUrl? = originalHttpUrl.newBuilder()


                    .build()
                val requestBuilder: Request.Builder = original.newBuilder()

                val request1: Request = requestBuilder

                     .build()
                it.proceed(request1 )
            })
            .build()

    }


    @Singleton
    @Provides
    fun provideRepoImp(retrofitService: ApiEndPoints): RepoImpl {
        return RepoImpl(retrofitService)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }
    @Provides
    fun provideApiEndPoints(retrofit: Retrofit): ApiEndPoints {
        return retrofit.create(ApiEndPoints::class.java)
    }
}