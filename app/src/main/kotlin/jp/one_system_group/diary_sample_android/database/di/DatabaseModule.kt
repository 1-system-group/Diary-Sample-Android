package jp.one_system_group.diary_sample_android.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.one_system_group.diary_sample_android.database.AppDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AppDatabase::class.java, "DiarySample.db")
        .addMigrations(object: Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                "CREATE TABLE diaryContent (" +
                        " id INTEGER PRIMARY KEY NOT NULL, " +
                        " title TEXT NOT NULL, " +
                        " content TEXT NOT NULL" +
                        ");")
            }
        })
        .build()

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase) = db.diaryDao()

    @Singleton
    @Provides
    fun provideDaoContent(db: AppDatabase) = db.diaryContentDao()
}