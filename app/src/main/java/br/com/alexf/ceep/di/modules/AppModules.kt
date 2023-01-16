package br.com.alexf.ceep.di.modules

import android.content.Context
import androidx.room.Room
import br.com.alexf.ceep.database.AppDatabase
import br.com.alexf.ceep.database.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun database(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "ceep.db"
        ).build()
    }

    @Provides
    fun noteDao(database: AppDatabase): NoteDao {
        return database.noteDao()
    }

}