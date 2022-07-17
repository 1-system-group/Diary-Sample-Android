package jp.one_system_group.diary_sample_android.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import jp.one_system_group.diary_sample_android.api.WebService
import jp.one_system_group.diary_sample_android.model.ReferenceMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

interface ReferenceMessageSource {
    suspend fun getReferenceMessage(id : Int) : ReferenceMessage
}

@Module
@InstallIn(ViewModelComponent::class)
class ReferenceMessageSourceModule {
    @Provides
    fun provideReferenceMessageSource(source: ReferenceMessageSourceImpl) : ReferenceMessageSource {
        return source
    }
}

class ReferenceMessageSourceImpl @Inject constructor(
    private val webService: WebService

):ReferenceMessageSource {

    override suspend fun getReferenceMessage(id : Int) : ReferenceMessage {
        val response = webService.getReferenceMessage(id)
        if (response.isSuccessful) {
            return requireNotNull(response.body())
        }
        throw Exception()
//        throw NetworkException(response.code(), response.errorBody().toString())
    }
}

interface ReferenceMessageApi {
    suspend fun fetchReferenceMessage(): Response<ReferenceMessage>
}

class RReferenceMessageApiImpl @Inject constructor (
    private val webService: WebService
) : ReferenceMessageApi {
    override suspend fun fetchReferenceMessage() : Response<ReferenceMessage> =
        withContext(Dispatchers.IO) { webService.getReferenceMessage(1) }
}
