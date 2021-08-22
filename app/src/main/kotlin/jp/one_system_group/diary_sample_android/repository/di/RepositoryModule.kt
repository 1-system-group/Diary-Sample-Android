package jp.one_system_group.diary_sample_android.repository.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.one_system_group.diary_sample_android.api.WebService
import jp.one_system_group.diary_sample_android.repository.DiaryRepository
import jp.one_system_group.diary_sample_android.repository.DiaryRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideDiaryRepository(
        webService: WebService
    ): DiaryRepository {
        return DiaryRepositoryImpl(webService)
    }
}