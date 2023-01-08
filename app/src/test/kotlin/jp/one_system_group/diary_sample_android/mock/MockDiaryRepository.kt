package jp.one_system_group.diary_sample_android.mock

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import jp.one_system_group.diary_sample_android.api.WebService
import jp.one_system_group.diary_sample_android.database.AppDatabase
import jp.one_system_group.diary_sample_android.repository.DiaryRepository
import jp.one_system_group.diary_sample_android.repository.DiaryRepositoryImpl
import mockwebserver3.Dispatcher
import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import mockwebserver3.RecordedRequest
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MockDiaryRepository {
    private var mockWebServer = MockWebServer()
    private var db: AppDatabase
    private var mockDiaryRepository: DiaryRepository

    init {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().setBody("[{\"id\":${page(request)},\"title\":\"テスト\",\"postDate\":\"2022/01/01\"}]")
                    .setResponseCode(200)

            }
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val webService = retrofit.create(WebService::class.java)
        mockWebServer.start()

        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), AppDatabase::class.java
        ).build()
        mockDiaryRepository = DiaryRepositoryImpl(webService, db.diaryDao(), db.diaryContentDao())
    }

    fun getMock(): DiaryRepository {
        return mockDiaryRepository
    }

    fun close() {
        mockWebServer.shutdown()
        db.close()
    }

    private fun page(request: RecordedRequest): String {
        return request.path?.replace("/lists/", "") ?: "0"
    }
}