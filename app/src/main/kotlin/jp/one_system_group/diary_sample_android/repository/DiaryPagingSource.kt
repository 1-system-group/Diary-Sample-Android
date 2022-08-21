package jp.one_system_group.diary_sample_android.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import jp.one_system_group.diary_sample_android.model.DiaryRow

class DiaryPagingSource(
    private val diaryRepository: DiaryRepository
) : PagingSource<Int, DiaryRow>() {

    override suspend fun load(
        params: LoadParams<Int>,
    ): LoadResult<Int, DiaryRow> {
        // Start refresh at page 1 if undefined.
        val nextPage = params.key ?: 1
        return try {
            val response = diaryRepository.getDiaryListFromWeb(nextPage).body()
            LoadResult.Page(
                data = response!!,
                // 前ページは無し
                prevKey = null,
                // nextKeyがnullの場合、次ページが存在しない
                nextKey = if (response.isEmpty()) null else nextPage.plus(1),
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).

            // ネットワークエラーの場合、DBからデータを取得する
            val response = diaryRepository.getDiaryListFromDb(nextPage)
            return LoadResult.Page(
                data = response,
                // 前ページは無し
                prevKey = null,
                // nextKeyがnullの場合、次ページが存在しない
                nextKey = if (response.isEmpty()) null else nextPage.plus(1),
            )
        }
    }

    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, DiaryRow>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}