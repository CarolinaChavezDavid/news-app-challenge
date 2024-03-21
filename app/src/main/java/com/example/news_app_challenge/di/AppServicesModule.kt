package com.example.news_app_challenge.di

import com.example.news_app_challenge.data.services.NewsApiServices
import com.example.news_app_challenge.data.services.UserApiServices
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
    private const val baseUrlPlaceHolder = "https://jsonplaceholder.org/"

    private val logging = HttpLoggingInterceptor()
    private val client = OkHttpClient.Builder().addInterceptor(logging).build()
    private val retrofitService: Retrofit by lazy {
        Retrofit.Builder().baseUrl(baseUrlPlaceHolder)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideNewsApiServices(): NewsApiServices {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return retrofitService.create(NewsApiServices::class.java)
    }

    @Provides
    fun provideUsersApiServices(): UserApiServices {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return retrofitService.create(UserApiServices::class.java)
    }
}
