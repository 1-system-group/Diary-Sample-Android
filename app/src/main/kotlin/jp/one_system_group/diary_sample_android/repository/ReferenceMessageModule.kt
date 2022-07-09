package jp.one_system_group.diary_sample_android.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import jp.one_system_group.diary_sample_android.model.ReferenceMessage
import jp.one_system_group.diary_sample_android.viewmodel.ReferenceMessageViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
class ReferenceMessageModule {
    @Provides
    fun provideGetReferenceMessageUseCase(
        getReferenceMessageUseCase : GetReferenceMessageUseCaseImpl
    ): GetReferenceMessageUseCase {
        return getReferenceMessageUseCase
    }
}

interface GetReferenceMessageUseCase {
    suspend operator fun invoke(): Flow<ReferenceMessage>
}

class GetReferenceMessageUseCaseImpl @Inject constructor(
    private val repository: ReferenceMessageRepository
) : GetReferenceMessageUseCase {
    override suspend operator fun invoke (

    ) : Flow<ReferenceMessage> =
        repository.getReferenceMessage()
}