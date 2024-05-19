package jp.one_system_group.diary_sample_android.repository.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.one_system_group.diary_sample_android.api.WebService
import jp.one_system_group.diary_sample_android.database.DiaryContentDao
import jp.one_system_group.diary_sample_android.database.DiaryDao
import jp.one_system_group.diary_sample_android.repository.AuthRepository
import jp.one_system_group.diary_sample_android.repository.AuthRepositoryImpl
import jp.one_system_group.diary_sample_android.repository.DiaryRepository
import jp.one_system_group.diary_sample_android.repository.DiaryRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        webService: WebService,
    ): AuthRepository {
        return AuthRepositoryImpl(webService)
    }

    @Provides
    @Singleton
    fun provideDiaryRepository(
        webService: WebService,
        dao: DiaryDao,
        daoContent: DiaryContentDao
    ): DiaryRepository {
        return DiaryRepositoryImpl(webService, dao, daoContent)
    }
}