package jp.one_system_group.diary_sample_android.api

import jp.one_system_group.diary_sample_android.model.Diary
import jp.one_system_group.diary_sample_android.model.DiaryRow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    // 日記の一覧を取得
    @GET("lists/{page}")
    suspend fun requestDiaryList(@Path("page") page: Int): Response<List<DiaryRow>>
    // 日記の内容を取得
    @GET("diary/{id}")
    suspend fun getDiary(@Path("id") id : Int): Response<Diary>
}