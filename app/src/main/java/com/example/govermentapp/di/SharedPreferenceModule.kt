package com.example.govermentapp.di

import android.content.Context
import android.content.SharedPreferences
import com.example.govermentapp.GovernmentApp
import com.example.govermentapp.R
import com.example.govermentapp.data.repositories.SharedPreferenceRepositoryImplementation
import com.example.govermentapp.domain.SharedPreferenceRepository
import com.example.govermentapp.ui.GovernmentActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceModule {
    @Singleton
    @Provides
    fun provideSharePreference(@ApplicationContext context: Context): SharedPreferenceRepository {
        val sharedPreferences = context.getSharedPreferences(
            GovernmentApp.instance.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
        return SharedPreferenceRepositoryImplementation(sharedPreferences,sharedPreferences.edit())
    }
}