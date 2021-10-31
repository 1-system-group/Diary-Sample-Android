package jp.one_system_group.diary_sample_android.model

import jp.one_system_group.diary_sample_android.database.DiaryEntity

data class DiaryRow(
    val id: Int,
    val title: String,
    val postDate: String,
) {
    fun toDiaryEntity(page: Int): DiaryEntity {
        return DiaryEntity(
            id = id,
            title = title,
            postDate = postDate,
            page = page
        )
    }
}