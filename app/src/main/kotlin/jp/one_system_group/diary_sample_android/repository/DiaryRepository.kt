package jp.one_system_group.diary_sample_android.repository

import jp.one_system_group.diary_sample_android.api.WebService
import jp.one_system_group.diary_sample_android.database.DiaryContentDao
import jp.one_system_group.diary_sample_android.database.DiaryContentEntity
import jp.one_system_group.diary_sample_android.database.DiaryDao
import jp.one_system_group.diary_sample_android.model.Diary
import jp.one_system_group.diary_sample_android.model.DiaryRow
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

interface DiaryRepository {
    suspend fun getDiaryListFromWeb(page: Int): Response<List<DiaryRow>>
    suspend fun getDiaryListFromDb(page: Int): List<DiaryRow>
    fun getDiary(id : Int): Flow<Diary>
}

class DiaryRepositoryImpl @Inject constructor(
    private val webService: WebService,
    private val dao: DiaryDao,
    private val daoContent: DiaryContentDao
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

    override fun getDiary(
        id : Int
    ) : Flow<Diary> = flow {
        emit(getDiarySuspend(id))
    }

    private suspend fun getDiarySuspend(id : Int) : Diary {
        val response = webService.getDiary(id)
        if (response.isSuccessful) {
            // APIから取得したデータをDBに保存しておく
            withContext(Dispatchers.IO) {
                val entity = DiaryContentEntity(requireNotNull(response.body()).id, requireNotNull(response.body()).title, requireNotNull(response.body()).content)
                daoContent.DeleteById(id)
                daoContent.Insert(entity)
            }
            return requireNotNull(response.body())
        }

        // APIから取得できなかったららDBから取得する
        var diary = Diary(-1,"エラー", "日記が見つかりませんでした")
        runBlocking {
            withContext(Dispatchers.IO) {
                // 例外が発生しても日記の表示処理を続ける
                try {
                    diary = daoContent.findContentById(id).toDiary()
                } catch (e: Exception) {
                }
            }
        }
        return diary
    }
}