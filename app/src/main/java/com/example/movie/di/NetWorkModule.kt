package com.example.movie.di
import com.example.movie.BuildConfig
import com.example.movie.data.remote.ApiEndPoints
import com.example.movie.data.repo.RepoImpl
import com.example.movie.utils.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
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
        return OkHttpClient.Builder()
            .readTimeout(Utils.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(Utils.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(90, TimeUnit.SECONDS)
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
            .baseUrl(BuildConfig.BASE_URL).build()
    }
    @Provides
    fun provideApiEndPoints(retrofit: Retrofit): ApiEndPoints {
        return retrofit.create(ApiEndPoints::class.java)
    }
}