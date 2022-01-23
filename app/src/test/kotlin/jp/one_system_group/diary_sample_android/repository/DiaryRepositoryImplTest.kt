package jp.one_system_group.diary_sample_android.repository

import jp.one_system_group.diary_sample_android.mock.MockDiaryRepository
import jp.one_system_group.diary_sample_android.model.DiaryRow
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test


class DiaryRepositoryImplTest {
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
    fun getDiaryListFromWebTest(): Unit = runBlocking {
        val response = mockRepository.getMock().getDiaryListFromWeb(1)
        assertThat(response.isSuccessful).isTrue
        assertThat(response.body()).isEqualTo(listOf(DiaryRow(1, "テスト", "2022/01/01")))
    }
}