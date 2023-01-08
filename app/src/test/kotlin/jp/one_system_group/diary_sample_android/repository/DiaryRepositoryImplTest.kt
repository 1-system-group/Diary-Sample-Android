package jp.one_system_group.diary_sample_android.repository

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


    @Test
    fun getDiaryListFromDbTest(): Unit = runBlocking {
        // DBに登録しておく
        mockRepository.getMock().getDiaryListFromWeb(1)

        val body = mockRepository.getMock().getDiaryListFromDb(1)
        assertThat(body).isEqualTo(listOf(DiaryRow(1, "テスト", "2022/01/01")))
    }
}