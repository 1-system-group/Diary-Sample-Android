package jp.one_system_group.diary_sample_android.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import jp.one_system_group.diary_sample_android.model.Diary

@Entity(tableName = "diaryContent")
data class DiaryContentEntity (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "content")
    val content: String
) {
    fun toDiary(): Diary {
        return Diary(
            id = id,
            title = title,
            content = content
        )
    }
}