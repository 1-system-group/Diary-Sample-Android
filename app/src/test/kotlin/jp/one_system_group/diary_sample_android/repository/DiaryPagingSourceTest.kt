package jp.one_system_group.diary_sample_android.repository

import androidx.paging.PagingSource
import androidx.test.ext.junit.runners.AndroidJUnit4
import jp.one_system_group.diary_sample_android.mock.MockDiaryRepository
import jp.one_system_group.diary_sample_android.model.DiaryRow
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [31])
@RunWith(AndroidJUnit4::class)
class DiaryPagingSourceTest {
    private lateinit var mockRepository: MockDiaryRepository

    @Before
    fun setUp() {
        mockRepository = MockDiaryRepository()
    }

    @After
    fun tearDown() {
        mockRepository.close()

    }

    @Test
    fun loadTest(): Unit = runBlocking {
        val pagingSource = DiaryPagingSource(mockRepository.getMock())
        val expectedPageKey = (1..5).toList() // 1～5ページまでテスト
        expectedPageKey.forEach {
            assertThat(
                PagingSource.LoadResult.Page(
                    data = listOf(DiaryRow(it, "テスト", "2022/01/01")),
                    prevKey = null,
                    nextKey = it + 1 // 次のページ番号が取得できること
                )
            ).isEqualTo(
                pagingSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = it, // 現在のページを指定して次ページを読み込み
                        loadSize = 10,
                        placeholdersEnabled = false
                    )
                )
            )
        }
    }
}