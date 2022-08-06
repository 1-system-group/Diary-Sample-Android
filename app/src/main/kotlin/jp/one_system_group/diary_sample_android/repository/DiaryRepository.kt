package jp.one_system_group.diary_sample_android.repository

import jp.one_system_group.diary_sample_android.api.WebService
import jp.one_system_group.diary_sample_android.model.Diary
import jp.one_system_group.diary_sample_android.model.DiaryRow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

interface DiaryRepository {
    suspend fun getDiaryList(): Response<List<DiaryRow>>
    fun getDiary(id : Int): Flow<Diary>
}

class DiaryRepositoryImpl @Inject constructor(
    private val webService: WebService
) : DiaryRepository {
    override suspend fun getDiaryList(): Response<List<DiaryRow>> =
        withContext(Dispatchers.IO) { webService.requestDiaryList(1) }

    override fun getDiary(
        id : Int
    ) : Flow<Diary> = flow {
        emit(getDiarySuspend(id))
    }

    private suspend fun getDiarySuspend(id : Int) : Diary {
        val response = webService.getDiary(id)
        if (response.isSuccessful) {
            return requireNotNull(response.body())
        }
        throw Exception()
//        throw NetworkException(response.code(), response.errorBody().toString())
    }
}