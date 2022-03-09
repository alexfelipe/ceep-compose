package br.com.alexf.ceep.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.alexf.ceep.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun findAll(): Flow<List<NoteEntity>>

    @Insert
    suspend fun save(noteEntity: NoteEntity)

}
