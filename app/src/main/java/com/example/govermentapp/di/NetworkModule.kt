package com.example.govermentapp.di

import android.location.LocationManager
import com.example.govermentapp.data.GovernmentRepositoryImplementation
import com.example.govermentapp.data.datasource.database.dao.GovernmentInstitutionsDao
import com.example.govermentapp.data.repositories.GovernmentRepositoryImplementation
import com.example.govermentapp.data.datasource.network.GobApiClient
import com.example.govermentapp.domain.GovernmentRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://api.datos.gob.mx/"

@Module(includes = [DatabaseModule::class, UtilsModule::class])
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providePokeApiClient(retrofit: Retrofit) : GobApiClient{
        return retrofit.create(GobApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideGovernmentRepository(
        apiService:GobApiClient,
        governmentInstitutionsDao: GovernmentInstitutionsDao,
        fusedLocationProviderClient: FusedLocationProviderClient,
        locationManager: LocationManager
    ): GovernmentRepository {
        return GovernmentRepositoryImplementation(apiService, governmentInstitutionsDao, fusedLocationProviderClient, locationManager, Firebase.auth)
    }
}