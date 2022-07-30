package jp.one_system_group.diary_sample_android.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import jp.one_system_group.diary_sample_android.api.WebService
import jp.one_system_group.diary_sample_android.model.Diary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

interface DiarySource {
    suspend fun getDiary(id : Int) : Diary
}

@Module
@InstallIn(ViewModelComponent::class)
class DiarySourceModule {
    @Provides
    fun provideDiarySource(source: DiarySourceImpl) : DiarySource {
        return source
    }
}

class DiarySourceImpl @Inject constructor(
    private val webService: WebService

):DiarySource {

    override suspend fun getDiary(id : Int) : Diary {
        val response = webService.getDiary(id)
        if (response.isSuccessful) {
            return requireNotNull(response.body())
        }
        throw Exception()
//        throw NetworkException(response.code(), response.errorBody().toString())
    }
}

interface DiaryApi {
    suspend fun fetchDiary(): Response<Diary>
}

class DiaryApiImpl @Inject constructor (
    private val webService: WebService
) : DiaryApi {
    override suspend fun fetchDiary() : Response<Diary> =
        withContext(Dispatchers.IO) { webService.getDiary(1) }
}
