package jp.one_system_group.diary_sample_android.repository

import jp.one_system_group.diary_sample_android.api.WebService
import jp.one_system_group.diary_sample_android.model.DiaryRow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

interface DiaryRepository {
    suspend fun getDiaryList(): Response<List<DiaryRow>>
}

class DiaryRepositoryImpl @Inject constructor(
    private val webService: WebService
) : DiaryRepository {
    override suspend fun getDiaryList(): Response<List<DiaryRow>> =
        withContext(Dispatchers.IO) { webService.requestDiaryList(1) }
}