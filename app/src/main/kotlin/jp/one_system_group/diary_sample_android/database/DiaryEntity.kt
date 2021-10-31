package jp.one_system_group.diary_sample_android.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import jp.one_system_group.diary_sample_android.model.DiaryRow

@Entity(tableName = "diary")
data class DiaryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "post_date")
    val postDate: String,
    @ColumnInfo(name = "page")
    val page: Int
) {
    fun toDiaryRow(): DiaryRow {
        return DiaryRow(
            id = id,
            title = title,
            postDate = postDate
        )
    }
}