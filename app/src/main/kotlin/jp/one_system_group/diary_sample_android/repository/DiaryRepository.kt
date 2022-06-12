package jp.one_system_group.diary_sample_android.repository

import jp.one_system_group.diary_sample_android.api.WebService
import jp.one_system_group.diary_sample_android.database.DiaryDao
import jp.one_system_group.diary_sample_android.model.DiaryRow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

interface DiaryRepository {
    suspend fun getDiaryListFromWeb(page: Int): Response<List<DiaryRow>>
    suspend fun getDiaryListFromDb(page: Int): List<DiaryRow>
}

class DiaryRepositoryImpl @Inject constructor(
    private val webService: WebService,
    private val dao: DiaryDao
) : DiaryRepository {
    override suspend fun getDiaryListFromWeb(page: Int): Response<List<DiaryRow>> =
        withContext(Dispatchers.IO) {
            delay(3000)
            val response = webService.requestDiaryList(page)
            val diaryList = response.body()
            if (diaryList != null) {
                // DBに保存する
                dao.deleteByPage(page)
                diaryList.forEach {
                    dao.insert(
                        it.toDiaryEntity(page)
                    )
                }
            }
            response
        }

    override suspend fun getDiaryListFromDb(page: Int): List<DiaryRow> {
        return withContext(Dispatchers.IO) {
            delay(3000)
            val diaries = dao.findByPage(page = page)
            diaries.map { it.toDiaryRow() }
        }
    }
}