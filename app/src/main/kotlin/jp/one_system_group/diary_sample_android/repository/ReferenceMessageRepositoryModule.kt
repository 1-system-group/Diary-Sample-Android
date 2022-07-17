package jp.one_system_group.diary_sample_android.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import jp.one_system_group.diary_sample_android.api.WebService
import jp.one_system_group.diary_sample_android.model.ReferenceMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class ReferenceMessageRepositoryModule {
    @Provides
    fun provideReferenceMessageRepository(repository : ReferenceMessageRepositoryImpl) :ReferenceMessageRepository {
        return repository
    }
}

@Module
@InstallIn(SingletonComponent::class)
class DispatcerModule {
    @Provides
    @Singleton
    fun provideIODispatcher() : CoroutineDispatcher {
        return Dispatchers.IO
    }
}


interface ReferenceMessageRepository {
    fun getReferenceMessage(): Flow<ReferenceMessage>
}

class ReferenceMessageRepositoryImpl @Inject constructor(
    private val source: ReferenceMessageSource
) :ReferenceMessageRepository {

    override fun getReferenceMessage(
    ) : Flow<ReferenceMessage> = flow {
        emit(source.getReferenceMessage(1))
    }
}
