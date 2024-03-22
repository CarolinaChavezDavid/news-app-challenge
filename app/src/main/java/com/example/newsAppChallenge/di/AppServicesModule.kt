package com.example.newsAppChallenge.di

import com.example.newsAppChallenge.data.services.NewsApiServices
import com.example.newsAppChallenge.data.services.UserApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppServicesModule {
    private const val BASE_URL_PLACEHOLDER = "https://jsonplaceholder.org/"

    private val logging = HttpLoggingInterceptor()
    private val client = OkHttpClient.Builder().addInterceptor(logging).build()
    private val retrofitService: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL_PLACEHOLDER)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideNewsApiServices(): NewsApiServices {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder().addInterceptor(logging).build()

        return Retrofit.Builder().baseUrl(BASE_URL_PLACEHOLDER)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(NewsApiServices::class.java)
    }

    @Provides
    fun provideUsersApiServices(): UserApiServices {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return retrofitService.create(UserApiServices::class.java)
    }
}
