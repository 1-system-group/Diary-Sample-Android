package jp.one_system_group.diary_sample_android.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DiaryEntity::class, DiaryContentEntity::class), version = 2,  exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun diaryDao(): DiaryDao
    abstract fun diaryContentDao(): DiaryContentDao
}