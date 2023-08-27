package jp.one_system_group.diary_sample_android.api.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.one_system_group.diary_sample_android.api.AuthorizationInterceptor
import jp.one_system_group.diary_sample_android.api.WebService
import jp.one_system_group.diary_sample_android.infra.TokenManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebServiceModule {
    @Singleton
    @Provides
    fun providePostApi(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(AuthorizationInterceptor(
                TokenManager(
                    context,
                )
            ))
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl("") // APIのベースURLを設定する
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
}