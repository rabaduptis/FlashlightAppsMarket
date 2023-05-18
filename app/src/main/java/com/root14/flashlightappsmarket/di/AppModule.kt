package com.root14.flashlightappsmarket.di

import android.app.Application
import androidx.room.Room
import com.root14.flashlightappsmarket.BuildConfig
import com.root14.flashlightappsmarket.data.AppDatabase
import com.root14.flashlightappsmarket.data.dao.ColoredLightDao
import com.root14.flashlightappsmarket.data.dao.FlashlightDao
import com.root14.flashlightappsmarket.data.dao.SOSAlertDao
import com.root14.flashlightappsmarket.network.Utility
import com.root14.flashlightappsmarket.network.api.ApiHelper
import com.root14.flashlightappsmarket.network.api.ApiHelperImpl
import com.root14.flashlightappsmarket.network.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "app_database.db")
            .build()
    }

    @Provides
    fun provideFlashlightDao(appDatabase: AppDatabase): FlashlightDao {
        return appDatabase.flashlightDao()
    }

    @Provides
    fun provideColoredLightDao(appDatabase: AppDatabase): ColoredLightDao {
        return appDatabase.coloredLightDao()
    }

    @Provides
    fun provideSOSAlertDao(appDatabase: AppDatabase): SOSAlertDao {
        return appDatabase.sosAlertDao()
    }

    @Provides
    fun provideBaseUrl() = Utility.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper
}
