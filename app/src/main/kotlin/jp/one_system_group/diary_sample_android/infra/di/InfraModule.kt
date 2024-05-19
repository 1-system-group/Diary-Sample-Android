package jp.one_system_group.diary_sample_android.infra.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.one_system_group.diary_sample_android.infra.TokenManager
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class InfraModule {

    @Provides
    @Singleton
    fun provideTokenManager(
        @ApplicationContext context: Context
    ) = TokenManager(
        context
    )
}