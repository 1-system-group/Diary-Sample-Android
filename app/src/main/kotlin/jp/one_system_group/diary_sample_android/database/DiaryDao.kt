package jp.one_system_group.diary_sample_android.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DiaryDao {
    @Query("SELECT * FROM diary WHERE page = :page ORDER BY post_date DESC, id DESC")
    fun findByPage(page: Int): List<DiaryEntity>

    @Insert
    fun insert(vararg diary: DiaryEntity)

    @Query("DELETE FROM diary WHERE page = :page")
    fun deleteByPage(page: Int)
}