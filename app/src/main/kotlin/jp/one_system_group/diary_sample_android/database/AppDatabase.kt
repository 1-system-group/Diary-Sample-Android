package jp.one_system_group.diary_sample_android.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DiaryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun diaryDao(): DiaryDao
}