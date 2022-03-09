package br.com.alexf.ceep.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.alexf.ceep.database.dao.NoteDao
import br.com.alexf.ceep.database.entity.NoteEntity

@Database(
    version = 1,
    entities = [NoteEntity::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}