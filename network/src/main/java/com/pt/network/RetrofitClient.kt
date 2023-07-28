package com.pt.network

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitClient {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor("live_zWNkvFjwF4dSIjFOC8AvOICwQphXouFaCbTcTwdkaPtieIMMwzySImKQX6rRMYXU"))
        .build()

    @Provides
    fun provideRetrofit(
        gson: Gson,
        baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String = "https://api.thedogapi.com/"
}


class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("x-api-key", apiKey)
            .build()
        return chain.proceed(request)
    }
}