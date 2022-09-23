package jp.one_system_group.diary_sample_android.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DiaryContentDao {

    @Query("SELECT * FROM diaryContent WHERE id = :id")
    fun findContentById(id: Int): DiaryContentEntity

    @Insert
    fun Insert(vararg content: DiaryContentEntity)

    @Query("DELETE FROM diaryContent WHERE id = :id")
    fun DeleteById(id: Int)
}